package io.yigitucun.eventflow.message;

import java.time.Instant;

public class GlobalExceptionMessage {
    private String message;
    private int status;
    private final Instant timestamp = Instant.now();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
