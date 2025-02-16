package com.learner.learnrabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing_key.name}")
    private String routingKey;

    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //Binding RabbitMQ Queue with Exchange with routing_key.
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange()).with(routingKey);
    }


    /*These below infrastructure beans are also required by SpringBoot app to work with
      RabbitMQ Broker but these beans are auto-configured by SpringBoot.
      1. ConnectionFactory
      2. RabbitTemplate
      3. RabbitAdmin */


}
