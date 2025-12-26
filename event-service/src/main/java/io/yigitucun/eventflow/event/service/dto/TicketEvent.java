package io.yigitucun.eventflow.event.service.dto;

import java.io.Serializable;

public class TicketEvent implements Serializable {
    private int eventId;
    private int quantity;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

