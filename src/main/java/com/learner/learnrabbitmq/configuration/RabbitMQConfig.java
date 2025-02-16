package com.learner.learnrabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

    @Value("${rabbitmq.queue_json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.routing_json_key.name}")
    private String routingJsonKey;

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

    //To work with Json messages we have to explicitly RabbitTemplate with jason MessageConverter.

    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    //Binding json_queue to exchange to routing_json_key
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    //MeaageConverter to work with Json data
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

//    If a MessageConverter bean is defined, it is associated automatically to the auto-configured AmqpTemplate.

    //Explicitly Creating RabbitTemplate instance to set message converter
   /* @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }*/

}
