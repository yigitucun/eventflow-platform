package io.yigitucun.eventflow.message;

import java.time.Instant;
import java.util.Map;

public class ValidationExceptionMessage {
    private Map<String,String> validationErrors;
    private final Instant timestamp = Instant.now();
    private final int statusCode = 400;

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
