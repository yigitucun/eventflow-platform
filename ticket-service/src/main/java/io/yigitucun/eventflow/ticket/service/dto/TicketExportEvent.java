package io.yigitucun.eventflow.ticket.service.dto;

public class TicketExportEvent {
    private int eventId;
    private Integer quantity;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
