package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFanoutConfig {

    @Bean
    public Queue marketingFanoutQueue(){
        return new Queue("marketingFanoutQueue", false);
    }

    @Bean
    public Queue financeFanoutQueue(){
        return new Queue("financeFanoutQueue", false);
    }

    @Bean
    public Queue adminFanoutQueue(){
        return new Queue("adminFanoutQueue", false);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    public Binding marketingFanoutBinding(Queue marketingFanoutQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(marketingFanoutQueue).to(fanoutExchange);
    }

    @Bean
    public Binding financeFanoutBinding(Queue financeFanoutQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(financeFanoutQueue).to(fanoutExchange);
    }

    @Bean
    public Binding adminFanoutBinding(Queue adminFanoutQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(adminFanoutQueue).to(fanoutExchange);
    }
}
