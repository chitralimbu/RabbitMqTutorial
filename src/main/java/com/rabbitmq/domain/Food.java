package com.rabbitmq.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Food {
    private String name;
    private String country;
}
