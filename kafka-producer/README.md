# Kafka Producer

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

## Kafka producer tesztelése

Indítsuk el az alkalmazást

Egy terminált indítva a `docker ps` paranccsal kikeressük a kafka konténer {id}-jét

Lépjünk be a konténer shelljébe
```
docker exec -it {id} bash
```
```
cd opt/kafka/bin
```

Egy consumer-t állítsunk rá a `chat-rooms` topicra (terminálból a konténer shelljében az `opt/kafka/bin` mappából):

```
kafka-console-consumer.sh --bootstrap-server :9092 --topic chat-rooms

```

A send-message végpontra POST hívást küldve továbbítja azt a topicra

Post küldés curl segítségével:
```
curl --header "Content-Type: application/json"
--request POST
--data '{"message":"hello world", "from":"producer", "room":"testroom"}'
http://localhost:8081/send-message
```

Vagy az IntelliJ segítségével

```
POST http://localhost:8081/send-message
Content-Type: application/json

{"message":"hello world", "from":"producer", "room":"testroom"}

```

Ha jól csináltunk mindent akkor az előzőleg megnyitott terminálban megjelenik az üzenet

