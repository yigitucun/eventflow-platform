package io.yigitucun.eventflow.event.service.controller;

import io.yigitucun.eventflow.event.service.dto.requests.CreateEventRequest;
import io.yigitucun.eventflow.event.service.dto.requests.UpdateEventRequest;
import io.yigitucun.eventflow.event.service.dto.responses.EventResponse;
import io.yigitucun.eventflow.event.service.model.EventStatus;
import io.yigitucun.eventflow.event.service.service.EventService;
import io.yigitucun.eventflow.security.abstracts.RequireUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAll() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getById(@PathVariable int id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EventResponse>> getByStatus(@PathVariable EventStatus status) {
        return ResponseEntity.ok(eventService.getEventsByStatus(status));
    }

    @RequireUser
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CreateEventRequest request,
                                 @RequestHeader(value = "X-User-Id", required = false) String userId) {
        eventService.createEvent(request, userId);
        return ResponseEntity.status(201).build();
    }

    @RequireUser
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
                                    @Valid @RequestBody UpdateEventRequest request,
                                    @RequestHeader(value = "X-User-Id", required = false) String userId) {
        eventService.updateEvent(id, request, userId);
        return ResponseEntity.ok().build();
    }

    @RequireUser
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id,
                                    @RequestHeader(value = "X-User-Id", required = false) String userId) {
        eventService.deleteEvent(id, userId);
        return ResponseEntity.noContent().build();
    }

    @RequireUser
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable int id,
                                    @RequestHeader(value = "X-User-Id", required = false) String userId) {
        eventService.cancelEvent(id, userId);
        return ResponseEntity.ok().build();
    }
}
