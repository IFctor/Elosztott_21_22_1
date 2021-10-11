# Kafka Consumer

## Előfeltétel a kafka-producer futtatásához:
1. Hivatalos [kafka-docker](https://github.com/wurstmeister/kafka-docker) repository clone-ozása.

2. `docker-compose-single-broker.yml` módosítása a példához!

```
image: wurstmeister/kafka
KAFKA_ADVERTISED_HOST_NAME: localhost
```

3. Kafka konténer elindítása
```
docker-compose -f docker-compose-single-broker.yml up
```

## Kafka consumer tesztelése

Indítsuk el az alkalmazást

Egy terminált indítva a `docker ps` paranccsal kikeressük a kafka konténer {id}-jét

Lépjünk be a konténer shelljébe
```
docker exec -it {id} bash
```
```
cd opt/kafka/bin
```

Egy producer-t állítsunk rá a `chat-rooms` topicra (terminálból a konténer shelljében az `opt/kafka/bin` mappából):

```
kafka-console-producer.sh --bootstrap-server :9092 --topic chat-rooms
```

Az előzőleg megnyitott terminálba írjuk be az üzenetünket

Ha jól csináltunk mindent akkor az előzőleg elindított alkalmazás terminálján megjelenik az üzenet
