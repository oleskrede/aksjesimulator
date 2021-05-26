import os
import enum

class Profile(enum.Enum):
    LOCAL = 'LOCAL'
    DOCKER = 'DOCKER'

def get_config():
    profile = os.getenv('PROFILE', 'LOCAL')

    config = {
        'topic_name': 'quotes',
        'ose_url': 'https://bors.e24.no/#!/list/norway/quotes/ose/EQUITIES/',
        'hosts': 'localhost:29092',
        'geckodriver': './geckodriver'
    }

    if profile == Profile.DOCKER.value:
        docker_config(config)
    
    return config



def docker_config(config):
    config['hosts'] = 'kafka:9092'
