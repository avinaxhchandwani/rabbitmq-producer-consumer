package com.demo.service.impl;

import com.demo.service.MessageConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MessageConsumerTwo implements MessageConsumer {

    @Override
    @RabbitListener(queues = "${config.rabbitmq.queue.second}")
    public void consume(String message) {
        System.out.println("Recieved Message From RabbitMQ 2: " + message);
    }
}
