package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirectConfig {

    @Bean
    public Queue marketingQueue(){
        return new Queue("marketing.queue", false);
    }

    @Bean
    public Queue financeQueue(){
        return new Queue("finance.queue", false);
    }

    @Bean
    public Queue adminQueue(){
        return new Queue("admin.queue", false);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct-exchange");
    }

    @Bean
    public Binding marketingBinding(Queue marketingQueue, DirectExchange directExchange){
        return BindingBuilder.bind(marketingQueue).to(directExchange).with("marketing");
    }

    @Bean
    public Binding financeBinding(Queue financeQueue, DirectExchange directExchange){
        return BindingBuilder.bind(financeQueue).to(directExchange).with("finance");
    }

    @Bean
    public Binding adminBinding(Queue marketingQueue, DirectExchange directExchange){
        return BindingBuilder.bind(marketingQueue).to(directExchange).with("admin");
    }
}
