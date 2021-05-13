from bs4 import BeautifulSoup
from selenium import webdriver
import time
import json

ose_url = "https://bors.e24.no/#!/list/norway/quotes/ose/EQUITIES/"

geckodriver = './geckodriver'
options = webdriver.FirefoxOptions()
options.add_argument('-headless')

browser = webdriver.Firefox(executable_path=geckodriver, options=options)
browser.get(ose_url)
time.sleep(5)

html = browser.page_source
soup = BeautifulSoup(html, 'lxml')
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

