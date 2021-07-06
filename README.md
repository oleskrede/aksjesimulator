# aksjesimulator

## TODOs


- TODO pris/bank/konto templates ved ny konto
- TODO hente inn reelle aksjekursar
- TODO sette opp database
- TODO lagre kursar til database
- TODO vise aksjer
- TODO handle aksjer
- TODO endre konto
- TODO slette konto
- TODO profil endre passord
- TODO profil reset passord via email
- TODO vise endring over tid på kontovisning
- TODO vise endring over tid på aksjevisning
- TODO hente inn fleire aksjer
- TODO errorHandling (use of !!)
- TODO nedlasting av geckodriver i webscraper Dockerfile
- TODO Jackson instead of Gson mapper in Ktor?
 

## Dokumentasjon

#### Kjøre lokalt

Start Kafka med Docker:
```
$ docker-compose up
```

Bygge Docker-compose service på nytt:
```
$ docker-compose build --no-cache <service>
```

#### Kafka producer/consumer CLI i Docker
Finn navn på docker container og hopp inn i denne:
```
$ docker ps
$ docker exec -it aksjesimulator_kafka_1 bash
```
Opprett test-topic:
```
# kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1
--partitions 1 --topic test
```
Produsent:
```
# kafka-console-producer.sh --broker-list localhost:9092 --topic test
```
Konsument:
```
# kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test
```