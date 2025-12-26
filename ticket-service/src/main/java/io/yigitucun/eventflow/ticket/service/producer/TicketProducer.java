package io.yigitucun.eventflow.ticket.service.producer;

import io.yigitucun.eventflow.ticket.service.config.RabbitMqConfig;
import io.yigitucun.eventflow.ticket.service.dto.TicketExportEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketProducer {
    private final RabbitTemplate rabbitTemplate;

    public TicketProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTicketCreatedEvent(TicketExportEvent event) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY, event);
        System.out.println("RabbitMQ'ya mesaj fırlatıldı: " + event.getEventId());
    }
}
