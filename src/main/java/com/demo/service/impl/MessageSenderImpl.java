package com.demo.service.impl;

import com.demo.service.MessageProducer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class MessageSenderImpl implements MessageProducer {

    @Autowired
    private AmqpTemplate rabbitMQTemplate;

    @Value("${config.rabbitmq.exchange}")
    private String exchange;

    @Value("${config.rabbitmq.routingkey}")
    private String routingkey;

    @Override
    public void send(String message) {
        rabbitMQTemplate.convertAndSend(exchange, routingkey, message);
        System.out.println("Send msg = " + message);
    }
}
