package io.yigitucun.eventflow.ticket.service.dto.requests;

import io.yigitucun.eventflow.ticket.service.model.TicketStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateTicketStatusRequest {
    @NotNull(message = "Status is required")
    private TicketStatus status;

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}

