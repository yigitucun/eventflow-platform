package io.yigitucun.eventflow.handler;

import io.yigitucun.eventflow.exception.GlobalException;
import io.yigitucun.eventflow.message.GlobalExceptionMessage;
import io.yigitucun.eventflow.message.ValidationExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler
    public ResponseEntity<?> handleGlobalException(GlobalException e){
        GlobalExceptionMessage message = new GlobalExceptionMessage();
        message.setMessage(e.getMessage());
        message.setStatus(e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(message);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException  e){
        GlobalExceptionMessage message = new GlobalExceptionMessage();
        message.setMessage(e.getMessage());
        message.setStatus(404);
        return ResponseEntity.status(404).body(message);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e){
        ValidationExceptionMessage exceptionMessage = new ValidationExceptionMessage();
        exceptionMessage.setValidationErrors(new HashMap<>());
        for (FieldError error: e.getFieldErrors()){
            exceptionMessage.getValidationErrors().put(error.getField(),error.getDefaultMessage());
        }
        e.getBindingResult().getGlobalErrors().forEach(error->
                    exceptionMessage.getValidationErrors().put(error.getObjectName(),error.getDefaultMessage())
                );

        return ResponseEntity.status(400).body(exceptionMessage);
    }

}
