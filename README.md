# aksjesimulator

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

#### Arkitekturoversikt
![Bilde oversikt](./docs/docs/overview.png)