package com.pratice.microservice.service.impl;

import com.pratice.microservice.service.MessageConsumer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageConsumerImpl implements MessageConsumer {
    @Override
    @RabbitListener(queues = "${config.rabbitmq.queue}")
    public void consume(String message) {
        System.out.println("Recieved Message From RabbitMQ: " + message);

    }
}
