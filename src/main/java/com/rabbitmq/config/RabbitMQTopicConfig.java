package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQTopicConfig {

    @Bean
    public Queue marketingTopicQueue(){
        return new Queue("marketingTopicQueue", false);
    }

    @Bean
    public Queue financeTopicQueue(){
        return new Queue("financeTopicQueue", false);
    }

    @Bean
    public Queue adminTopicQueue(){
        return new Queue("adminTopicQueue", false);
    }

    @Bean
    public Queue allTopicQueue(){
        return new Queue("allTopicQueue", false);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic-exchange");
    }

    @Bean
    public Binding marketingTopicBinding(Queue marketingTopicQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(marketingTopicQueue).to(topicExchange).with("queue.fanout.marketing");
    }

    @Bean
    public Binding financeTopicBinding(Queue financeTopicQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(financeTopicQueue).to(topicExchange).with("queue.fanout.finance");
    }

    @Bean
    public Binding adminTopicBinding(Queue adminTopicQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(adminTopicQueue).to(topicExchange).with("queue.fanout.admin");
    }

    @Bean
    public Binding allTopicBinding(Queue allTopicQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(allTopicQueue).to(topicExchange).with("queue.fanout.*");
    }
}
