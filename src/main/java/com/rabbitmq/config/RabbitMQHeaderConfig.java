package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQHeaderConfig {

    @Bean
    public Queue marketingHeaderQueue(){
        return new Queue("marketingHeaderQueue", false);
    }

    @Bean
    public Queue financeHeaderQueue(){
        return new Queue("financeHeaderQueue", false);
    }

    @Bean
    public Queue adminHeaderQueue(){
        return new Queue("adminHeaderQueue", false);
    }

    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange("header-exchange");
    }

    @Bean
    public Binding marketingHeaderBinding(Queue marketingHeaderQueue, HeadersExchange headersExchange){
        return BindingBuilder.bind(marketingHeaderQueue).to(headersExchange).where("department").matches("marketing");
    }

    @Bean
    public Binding financeHeaderBinding(Queue financeHeaderQueue, HeadersExchange headersExchange){
        return BindingBuilder.bind(financeHeaderQueue).to(headersExchange).where("department").matches("finance");
    }

    @Bean
    public Binding adminHeaderBinding(Queue adminHeaderQueue, HeadersExchange headersExchange){
        return BindingBuilder.bind(adminHeaderQueue).to(headersExchange).where("department").matches("admin");
    }

}
