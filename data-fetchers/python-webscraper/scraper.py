import string
from bs4 import BeautifulSoup
from pykafka import KafkaClient
from pykafka.topic import Topic
from selenium import webdriver
import time
import json
import logging

from selenium.webdriver.firefox.webdriver import WebDriver

logging.basicConfig(filename='scraper.log', level=logging.INFO,
                    format='%(asctime)s %(message)s')

topic_name = 'quotes'
ose_url = 'https://bors.e24.no/#!/list/norway/quotes/ose/EQUITIES/'
# hosts = '0.0.0.0:9092'
hosts = 'kafka:9092'
geckodriver = './geckodriver'

logging.info('Started')

logging.info(
    f"Setting up Kafka client with hosts: '{hosts}' and topic: '{topic_name}'.")
client = KafkaClient(hosts=hosts)
topic = client.topics[topic_name]

logging.info(f"Setting up headless Firefox WebDriver.")
options = webdriver.FirefoxOptions()
options.add_argument('-headless')
browser = webdriver.Firefox(executable_path=geckodriver, options=options)


def scrape_for_exchange(exchange_url: string, browser: WebDriver) -> string:
    logging.info(f"Scraping quotes from {exchange_url}")
    browser.get(exchange_url)
    time.sleep(5)

    html = browser.page_source
    soup = BeautifulSoup(html, 'html5lib')
    quotes_table = soup.select('tbody')[-1]

    quotes = []
    timestamp = int(time.time())

    for row in quotes_table:

        if (len(row.get('class')) > 0):
            continue

        ticker, exchange = row.get('data-reactid')[6:].split("_")

        columns = row.select('td')
        name = columns[0].text
        price = columns[1].text

        quotes.append(
            {'ticker': ticker,
             'exchange': exchange,
             'name': name,
             'price': price,
             'timestamp': timestamp}
        )

    json_data = json.dumps(quotes)
    return json_data


def send_to_topic(topic: Topic, json_data: string):
    logging.info(f"Sending json_data to topic: '{topic.name}'")
    with topic.get_sync_producer() as producer:
        producer.produce(json_data.encode())


while True:
    print("Scraping data")
    json_data = scrape_for_exchange(ose_url, browser)
    print("Sending to kafka")
    send_to_topic(topic, json_data)
    print("Finished")
    logging.info(
        'Iteration complete. Waiting 15 minutes before next iteration.')
    time.sleep(60*15)
