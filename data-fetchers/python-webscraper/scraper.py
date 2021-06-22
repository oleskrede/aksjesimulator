import string
from bs4 import BeautifulSoup
from pykafka import KafkaClient
from pykafka.topic import Topic
from selenium import webdriver
from selenium.webdriver.firefox.webdriver import WebDriver
import time
import json
import logging
import datetime

from config import get_config

logging.basicConfig(filename='scraper.log', level=logging.INFO,
                    format='%(asctime)s %(message)s')

def log(message):
    print(datetime.datetime.now(), message)
    logging.info(message)

log('Loading config')
config = get_config()

topic_name = config['topic_name']
ose_url = config['ose_url']
hosts = config['hosts']
geckodriver = config['geckodriver']

log(
    f"Setting up Kafka client with hosts: '{hosts}' and topic: '{topic_name}'.")

client = None
while not type(client) is KafkaClient:
    try:
        client = KafkaClient(hosts=hosts)
    except:
        log("Could not initialize KafkaClient. Trying again in 5 seconds")
        time.sleep(5)


topic = client.topics[topic_name]

log(f"Setting up headless Firefox WebDriver.")
options = webdriver.FirefoxOptions()
options.add_argument('-headless')
browser = webdriver.Firefox(executable_path=geckodriver, options=options)


def scrape_for_exchange(exchange_url: string, browser: WebDriver) -> string:
    log(f"Scraping quotes from {exchange_url}")
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
    log(f"Sending json_data to topic: '{topic.name}'")
    with topic.get_sync_producer() as producer:
        producer.produce(json_data.encode())


while True:
    log("Scraping data")
    json_data = scrape_for_exchange(ose_url, browser)
    log("Sending to kafka")
    send_to_topic(topic, json_data)
    log('Iteration complete. Waiting 15 minutes before next iteration.')
    time.sleep(60*15)
