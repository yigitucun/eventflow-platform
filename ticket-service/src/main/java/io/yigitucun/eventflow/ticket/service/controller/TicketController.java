package io.yigitucun.eventflow.ticket.service.controller;

import io.yigitucun.eventflow.security.abstracts.RequireUser;
import io.yigitucun.eventflow.ticket.service.dto.requests.CreateTicketRequest;
import io.yigitucun.eventflow.ticket.service.dto.responses.TicketResponse;
import io.yigitucun.eventflow.ticket.service.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/buy")
    @RequireUser
    public ResponseEntity<TicketResponse> buyTicket(
            @Valid @RequestBody CreateTicketRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId
    ) {
        return ResponseEntity.status(201).body(ticketService.createTicket(request, userId));
    }

    @GetMapping("/{id}")
    @RequireUser
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable int id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping("/number/{ticketNumber}")
    @RequireUser
    public ResponseEntity<TicketResponse> getTicketByNumber(@PathVariable String ticketNumber) {
        return ResponseEntity.ok(ticketService.getTicketByNumber(ticketNumber));
    }

    @GetMapping("/my")
    @RequireUser
    public ResponseEntity<List<TicketResponse>> getMyTickets(
            @RequestHeader(value = "X-User-Id", required = false) String userId
    ) {
        return ResponseEntity.ok(ticketService.getMyTickets(userId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<TicketResponse>> getTicketsByEventId(@PathVariable int eventId) {
        return ResponseEntity.ok(ticketService.getTicketsByEventId(eventId));
    }

    @GetMapping("/event/{eventId}/active")
    public ResponseEntity<List<TicketResponse>> getActiveTicketsByEventId(@PathVariable int eventId) {
        return ResponseEntity.ok(ticketService.getActiveTicketsByEventId(eventId));
    }

    @GetMapping("/event/{eventId}/count")
    public ResponseEntity<Integer> getActiveTicketCount(@PathVariable int eventId) {
        return ResponseEntity.ok(ticketService.getActiveTicketCount(eventId));
    }

    @PostMapping("/{id}/cancel")
    @RequireUser
    public ResponseEntity<Void> cancelTicket(
            @PathVariable int id,
            @RequestHeader(value = "X-User-Id", required = false) String userId
    ) {
        ticketService.cancelTicket(id, userId);
        return ResponseEntity.noContent().build();
    }
}
