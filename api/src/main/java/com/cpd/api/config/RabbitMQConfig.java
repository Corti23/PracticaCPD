package com.cpd.api.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitMQConfig {
    
    // Queue of disk creation requests
    public static final String DISK_REQUEST_QUEUE = "disk-requests";
    public static final String DISK_STATUS_QUEUE = "disk-statuses";
    public static final String INSTANCE_REQUEST_QUEUE = "instance-requests";
    public static final String INSTANCE_STATUS_QUEUE = "instance-statuses";

    @Bean
    public Queue diskRequestQueue() {
        return new Queue(DISK_REQUEST_QUEUE, false);
    }

    @Bean
    public Queue diskStatusQueue() {
        return new Queue(DISK_STATUS_QUEUE, false);
    }

    @Bean
    public Queue instanceRequestQueue() {
        return new Queue(INSTANCE_REQUEST_QUEUE, false);
    }

    @Bean
    public Queue instanceStatusQueue() {
        return new Queue(INSTANCE_STATUS_QUEUE, false);
    }

    @Bean
    public MessageConverter messageConverter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of("java.util.*", "java.lang.*"));

        return converter;
    }
}