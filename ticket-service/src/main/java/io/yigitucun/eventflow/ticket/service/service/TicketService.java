package io.yigitucun.eventflow.ticket.service.service;

import io.yigitucun.eventflow.exception.GlobalException;
import io.yigitucun.eventflow.ticket.service.dto.TicketExportEvent;
import io.yigitucun.eventflow.ticket.service.dto.requests.CreateTicketRequest;
import io.yigitucun.eventflow.ticket.service.dto.responses.TicketResponse;
import io.yigitucun.eventflow.ticket.service.mapper.TicketMapper;
import io.yigitucun.eventflow.ticket.service.model.TicketStatus;
import io.yigitucun.eventflow.ticket.service.producer.TicketProducer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    private final TicketMapper ticketMapper;
    private final TicketProducer ticketProducer;

    public TicketService(TicketMapper ticketMapper, TicketProducer ticketProducer) {
        this.ticketMapper = ticketMapper;
        this.ticketProducer = ticketProducer;
    }

    @Transactional
    public TicketResponse createTicket(CreateTicketRequest request, String userId) {
        int userIdInt = Integer.parseInt(userId);

        int existingTickets = ticketMapper.countUserActiveTicketsForEvent(request.getEventId(), userIdInt);
        if (existingTickets > 0) {
            throw new GlobalException("You already have an active ticket for this event", HttpStatus.CONFLICT);
        }

        request.setStatus(TicketStatus.ACTIVE);
        request.setTicketNumber("TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        ticketMapper.insertTicket(request, userIdInt);

        TicketExportEvent event = new TicketExportEvent();
        event.setEventId(request.getEventId());
        event.setQuantity(1);
        ticketProducer.sendTicketCreatedEvent(event);

        return getTicketById(request.getId());
    }

    public TicketResponse getTicketById(int id) {
        return ticketMapper.findById(id)
                .orElseThrow(() -> new GlobalException("Ticket not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    public TicketResponse getTicketByNumber(String ticketNumber) {
        return ticketMapper.findByTicketNumber(ticketNumber)
                .orElseThrow(() -> new GlobalException("Ticket not found with number: " + ticketNumber, HttpStatus.NOT_FOUND));
    }

    public List<TicketResponse> getMyTickets(String userId) {
        return ticketMapper.findByUserId(Integer.parseInt(userId));
    }

    public List<TicketResponse> getTicketsByEventId(int eventId) {
        return ticketMapper.findByEventId(eventId);
    }

    public List<TicketResponse> getActiveTicketsByEventId(int eventId) {
        return ticketMapper.findByEventIdAndStatus(eventId, TicketStatus.ACTIVE);
    }

    @Transactional
    public void cancelTicket(int ticketId, String userId) {
        checkTicketOwnership(ticketId, userId);

        TicketResponse ticket = getTicketById(ticketId);
        if (ticket.getStatus() == TicketStatus.CANCELLED) {
            throw new GlobalException("Ticket is already cancelled", HttpStatus.BAD_REQUEST);
        }
        if (ticket.getStatus() == TicketStatus.COMPLETED) {
            throw new GlobalException("Cannot cancel a completed ticket", HttpStatus.BAD_REQUEST);
        }

        int updated = ticketMapper.updateStatus(ticketId, TicketStatus.CANCELLED);
        if (updated == 0) {
            throw new GlobalException("Failed to cancel ticket", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        TicketExportEvent event = new TicketExportEvent();
        event.setEventId(ticket.getEventId());
        event.setQuantity(-1);
        ticketProducer.sendTicketCreatedEvent(event);
    }

    @Transactional
    public void cancelTicketsByEventId(int eventId) {
        int cancelled = ticketMapper.cancelTicketsByEventId(eventId);
        if (cancelled > 0) {
            TicketExportEvent event = new TicketExportEvent();
            event.setEventId(eventId);
            event.setQuantity(-cancelled);
            ticketProducer.sendTicketCreatedEvent(event);
        }
    }

    public int getActiveTicketCount(int eventId) {
        return ticketMapper.countActiveTicketsByEventId(eventId);
    }

    private void checkTicketOwnership(int ticketId, String userId) {
        boolean isOwner = ticketMapper.isTicketOwner(ticketId, Integer.parseInt(userId));
        if (!isOwner) {
            throw new GlobalException("You don't have permission to access this ticket", HttpStatus.FORBIDDEN);
        }
    }
}
