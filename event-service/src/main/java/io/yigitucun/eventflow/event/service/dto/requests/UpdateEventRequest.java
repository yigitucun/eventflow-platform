package io.yigitucun.eventflow.event.service.dto.requests;

import io.yigitucun.eventflow.event.service.model.EventStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateEventRequest {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    @Future
    private LocalDateTime eventDate;
    @NotNull
    private String location;
    @NotNull
    @PositiveOrZero
    private BigDecimal price;
    @Min(value = 1)
    @Max(value = 100)
    @NotNull
    private Integer capacity;
    @Min(30)
    @Max(240)
    private int duration;
    private EventStatus status;

    public UpdateEventRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }
}

