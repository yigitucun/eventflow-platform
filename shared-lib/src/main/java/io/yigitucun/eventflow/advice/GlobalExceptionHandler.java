package io.yigitucun.eventflow.advice;

import io.yigitucun.eventflow.exceptions.GlobalException;
import io.yigitucun.eventflow.message.ExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleGlobalException(GlobalException e){
        ExceptionMessage message = new ExceptionMessage();
        message.setStatus(e.getStatus().value());
        message.setMessage(e.getMessage());
        return ResponseEntity.status(e.getStatus().value()).body(message);
    }
}
