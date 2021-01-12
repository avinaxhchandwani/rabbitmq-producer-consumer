# RabbitMQ - Producer - Consumer | Getting Started

## Requirements

For building and running the application you need:
- [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org)
- [RabbitMQ](https://www.rabbitmq.com/download.html)
- [Erlang](http://www.erlang.org/downloads)

## Prerequisite
RabbitMq should be installed prior to execute this code, I followed steps on [Tutlane](https://www.tutlane.com/tutorial/rabbitmq/rabbitmq-installation) to install RabbitMQ on my Windows 10.

## Run Application
You can build the application by using the maven build command like so:
```shell
mvn clean install -DskipTests
```
you can execute the application using maven springboot run command:
```shell
mvn spring-boot:run
```

## Detailed Description
```shell
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
config.rabbitmq.exchange=practice.exchange
config.rabbitmq.queue=practice.queue
config.rabbitmq.queue.second=practice.queue.second
config.rabbitmq.routingkey=practice.routingkey
```

As per above properties please create 1 exchange, two topics with given routing key.

after every thing is done, call a GET request: http://localhost:8080/message/producer?message=anymessage

you can see the logs to check if both listeners are getting messages.
