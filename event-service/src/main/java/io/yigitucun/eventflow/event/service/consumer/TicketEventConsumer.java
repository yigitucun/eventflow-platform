package io.yigitucun.eventflow.event.service.consumer;

import io.yigitucun.eventflow.event.service.config.RabbitMqConfig;
import io.yigitucun.eventflow.event.service.dto.TicketEvent;
import io.yigitucun.eventflow.event.service.service.EventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TicketEventConsumer {
    private final EventService eventService;

    public TicketEventConsumer(EventService eventService) {
        this.eventService = eventService;
    }

    @RabbitListener(queues = RabbitMqConfig.TICKET_QUEUE)
    public void handleTicketEvent(TicketEvent event) {
        System.out.println("Received ticket event for eventId: " + event.getEventId() + ", quantity: " + event.getQuantity());
        eventService.updateSoldTicketCount(event.getEventId(), event.getQuantity());
    }
}

