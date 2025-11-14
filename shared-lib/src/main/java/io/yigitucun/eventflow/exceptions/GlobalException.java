package io.yigitucun.eventflow.exceptions;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {

    private HttpStatus status;

    public GlobalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
