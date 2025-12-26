package io.yigitucun.eventflow.event.service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String TICKET_QUEUE = "ticket.queue";
    public static final String TICKET_EXCHANGE = "ticket.exchange";
    public static final String TICKET_ROUTING_KEY = "ticket.routing.key";

    @Bean
    public Queue ticketQueue() {
        return new Queue(TICKET_QUEUE);
    }

    @Bean
    public TopicExchange ticketExchange() {
        return new TopicExchange(TICKET_EXCHANGE);
    }

    @Bean
    public Binding ticketBinding(Queue ticketQueue, TopicExchange ticketExchange) {
        return BindingBuilder.bind(ticketQueue).to(ticketExchange).with(TICKET_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
