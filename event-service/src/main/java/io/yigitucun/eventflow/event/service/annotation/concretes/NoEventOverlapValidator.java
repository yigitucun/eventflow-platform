package io.yigitucun.eventflow.event.service.annotation.concretes;


import io.yigitucun.eventflow.event.service.annotation.abstracts.NoEventOverlap;
import io.yigitucun.eventflow.event.service.dto.requests.CreateEventRequest;
import io.yigitucun.eventflow.event.service.mapper.EventMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class NoEventOverlapValidator implements ConstraintValidator<NoEventOverlap, CreateEventRequest> {

    private final EventMapper eventMapper;

    public NoEventOverlapValidator(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Override
    public boolean isValid(CreateEventRequest createEventRequest, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime newStart = createEventRequest.getEventDate();
        LocalDateTime newEnd = newStart.plusMinutes(createEventRequest.getDuration());
        return !eventMapper.existsOverlappingEvents(newStart,newEnd);
    }
}
