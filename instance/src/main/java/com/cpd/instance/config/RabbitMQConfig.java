package com.cpd.instance.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitMQConfig {
    
    public static final String INSTANCE_REQUEST_QUEUE = "instance-requests";
    public static final String INSTANCE_STATUS_QUEUE = "instance-statuses";
    public static final String INSTANCE_QUEUE = "instance.queue";

    @Bean
    public Queue instanceQueue() {
        return new Queue(INSTANCE_QUEUE, false); // false = NO durable
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