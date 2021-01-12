package com.demo.service.impl;

import com.demo.service.MessageConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerImpl implements MessageConsumer {
    @Override
    @RabbitListener(queues = "${config.rabbitmq.queue}")
    public void consume(String message) {
        System.out.println("Recieved Message From RabbitMQ: " + message);

    }
}
