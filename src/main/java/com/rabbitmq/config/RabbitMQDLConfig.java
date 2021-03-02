package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDLConfig {

    @Bean
    public DirectExchange deadLetterExchange(){
        return new DirectExchange("deadLetterExchange");
    }

    @Bean
    public DirectExchange myExchange(){
        return new DirectExchange("myExchange");
    }

    @Bean
    public Queue dlq(){
        return QueueBuilder.durable("deadLetter.queue").build();
    }

    @Bean
    public Queue myQueue(){
        return QueueBuilder.durable("my.queue").withArgument("x-dead-letter-exchange", "deadLetterExchange")
                .withArgument("x-dead-letter-routing-key", "deadLetter").build();
    }

    @Bean
    public Binding DLQbinding(){
        return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with("deadLetter");
    }

    @Bean
    public Binding myBinding(){
        return BindingBuilder.bind(myQueue()).to(myExchange()).with("myQueue");
    }

}
