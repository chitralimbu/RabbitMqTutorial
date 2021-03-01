package com.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fanout")
public class FanoutWebController {

    private final AmqpTemplate amqpTemplate;

    public FanoutWebController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @GetMapping("/producer/{exchange}/{data}")
    public String producer(@PathVariable("exchange") String exchange, @PathVariable("data") String data){
        amqpTemplate.convertAndSend(exchange, "" , data);
        return "Fanout completed";
    }
}
