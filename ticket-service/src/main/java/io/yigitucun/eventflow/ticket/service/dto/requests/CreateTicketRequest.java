package io.yigitucun.eventflow.ticket.service.dto.requests;

import io.yigitucun.eventflow.ticket.service.model.TicketStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateTicketRequest {
    private int id;

    @NotNull(message = "Event ID is required")
    @Positive(message = "Event ID must be positive")
    private int eventId;

    private String ticketNumber;

    @Positive(message = "Price must be positive")
    private double price;

    private TicketStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
