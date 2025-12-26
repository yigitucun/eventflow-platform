package io.yigitucun.eventflow.event.service.dto.requests;

import io.yigitucun.eventflow.event.service.annotation.abstracts.NoEventOverlap;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoEventOverlap
public class CreateEventRequest {
    private int id;
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

    public @Min(30) @Max(240) int getDuration() {
        return duration;
    }

    public void setDuration(@Min(30) @Max(240) int duration) {
        this.duration = duration;
    }

    public CreateEventRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
