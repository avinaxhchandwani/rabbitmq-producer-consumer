package com.demo.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@EnableRabbit
@Configuration
public class RabbitMQConfig {
    @Value("${config.rabbitmq.queue}")
    String queueName;

    @Value("${config.rabbitmq.exchange}")
    String exchange;

    @Value("${config.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${config.rabbitmq.queue.second}")
    private String secondQueue;

    @Bean(name="secondQueue")
    Queue getSecondQueue() {
        return new Queue(secondQueue, false);
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    ConnectionFactory getConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(host + ":" + port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean(name = "rabbitListenerContainerFactory")
    SimpleRabbitListenerContainerFactory getContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(getConnectionFactory());
        factory.setMaxConcurrentConsumers(5);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }

}
