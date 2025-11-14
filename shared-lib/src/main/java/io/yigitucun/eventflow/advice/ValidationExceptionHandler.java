package io.yigitucun.eventflow.advice;

import io.yigitucun.eventflow.message.ValidationExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e){
        ValidationExceptionMessage message = new ValidationExceptionMessage();
        message.setValidationErrors(new HashMap<>());
        for (FieldError fieldError: e.getFieldErrors()){
            message.getValidationErrors().put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(400).body(message);
    }
}
