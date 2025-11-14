package io.yigitucun.eventflow.message;

import java.time.Instant;
import java.util.Date;

public class ExceptionMessage {
    private String message;
    private int status;
    private final Instant timestamp = Instant.now();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
