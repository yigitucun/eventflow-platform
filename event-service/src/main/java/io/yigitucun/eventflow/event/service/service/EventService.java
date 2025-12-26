package io.yigitucun.eventflow.event.service.service;

import io.yigitucun.eventflow.event.service.dto.requests.CreateEventRequest;
import io.yigitucun.eventflow.event.service.dto.requests.UpdateEventRequest;
import io.yigitucun.eventflow.event.service.dto.responses.EventResponse;
import io.yigitucun.eventflow.event.service.mapper.EventMapper;
import io.yigitucun.eventflow.event.service.model.EventRole;
import io.yigitucun.eventflow.event.service.model.EventStatus;
import io.yigitucun.eventflow.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventMapper eventMapper;

    public EventService(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public void createEvent(CreateEventRequest request, String userId) {
        eventMapper.insertEvent(request);
        eventMapper.addStaff(request.getId(), Integer.parseInt(userId), EventRole.OWNER);
    }

    public List<EventResponse> getAllEvents() {
        return eventMapper.findAll();
    }

    public EventResponse getEventById(int id) {
        return eventMapper.findById(id)
                .orElseThrow(() -> new GlobalException("Event not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    public List<EventResponse> getEventsByStatus(EventStatus status) {
        return eventMapper.findByStatus(status);
    }

    public void updateEvent(int eventId, UpdateEventRequest request, String userId) {
        checkEventPermission(eventId, userId);
        int updated = eventMapper.updateEvent(eventId, request);
        if (updated == 0) {
            throw new GlobalException("Event not found with id: " + eventId, HttpStatus.NOT_FOUND);
        }
    }

    public void deleteEvent(int eventId, String userId) {
        checkEventPermission(eventId, userId);
        int deleted = eventMapper.deleteById(eventId);
        if (deleted == 0) {
            throw new GlobalException("Event not found with id: " + eventId, HttpStatus.NOT_FOUND);
        }
    }

    public void cancelEvent(int eventId, String userId) {
        checkEventPermission(eventId, userId);
        int updated = eventMapper.updateStatus(eventId, EventStatus.CANCELLED);
        if (updated == 0) {
            throw new GlobalException("Event not found with id: " + eventId, HttpStatus.NOT_FOUND);
        }
    }


    private void checkEventPermission(int eventId, String userId) {
        boolean hasPermission = eventMapper.isEventOwnerOrModerator(eventId, Integer.parseInt(userId));
        if (!hasPermission) {
            throw new GlobalException("You don't have permission to modify this event", HttpStatus.FORBIDDEN);
        }
    }

    public void updateSoldTicketCount(int eventId, int quantity) {
        int updated = eventMapper.updateSoldTickets(eventId, quantity);
        if (updated == 0) {
            System.out.println("Event not found for ticket count update: " + eventId);
        } else {
            System.out.println("Updated sold tickets for event " + eventId + " by " + quantity);
        }
    }

    public int getAvailableTickets(int eventId) {
        return eventMapper.getAvailableTickets(eventId);
    }
}
