# Eureka microservices project

## Projekt futtatása:
A microservices_demo `pom.xml` fájlja modulok formájában tartalmazza az alprojekteket.
A projekt az alábbi modulokból épül fel:
* `eureka-server`
* `order-service`
* `storage-service`
* `gateway-service`

1. microservices_demo `pom.xml` -> Maven build

2. futtassuk az `eureka-server` projektet Spring Boot alkalmazásént
Eureka server manager felület: [http://localhost:8761/](http://localhost:8761/)

Egyetlen egy oszályt tartalmaz (EurekaServerApplication), melyet `@EnableEurekaServer` annotációval jelöltünk meg.

Az `application.properties` fájlban a következő beállításokat adtuk meg:

```java
server.port=8761
eureka.client.fetch-registry=false
eureka.client.register-with-eureka=false
```


3. futtassuk az `order-service`  modult Spring Boot alkalmazásként

Az `application.properties` fájlban a következő beállításokat adtuk meg:

```java
server.port=8080
spring.application.name=order-service
eureka.client.service-url.defautZone=http://localhost:8761/eureka/
```
Az alkalmazásunk a localhost:8080-on fog figyelni és beregisztrálja magát az előzőleg indított eureka szerverre a megadaott névvel.


4. futtasuk a `storage-service` modult Spring Boot alkalmazásként

Az `application.properties` fájlban a következő beállításokat adtuk meg:

```java
server.port=8081
spring.application.name=storage-service
eureka.client.service-url.defautZone=http://localhost:8761/eureka/
```
Az alkalmazásunk a localhost:8081-on fog figyelni és beregisztrálja magát az előzőleg indított eureka szerverre a megadaott névvel.

4. végül futtassuk a `gateway-service` modult egy külön terminálban Spring Boot alkalmazásként
A Gateway szolgáltatás közvetíti a kéréseket a microservice-ek felé, továbbítja a válaszokat és felel a load balance-olásért is.

Az `application.yml` fájlban a következő beállításokat adtuk meg:

```
server:
  port: 8000
spring:
  application:
    name: gateway-service
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
      routes:
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/**
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

5. Ha minden service felépült és beregisztrálta magát az Eureka registry-be, akkor a [http://localhost:8080/order](http://localhost:8080/order) címet meglátogatva a következő szöveget kapjuk válaszként:

```
Order confirmed! Remaining products: 99
```

A címet látogatva a számláló mindig eggyel csökkenni fog.
