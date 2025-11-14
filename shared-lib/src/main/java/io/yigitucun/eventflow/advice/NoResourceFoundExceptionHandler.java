package io.yigitucun.eventflow.advice;


import io.yigitucun.eventflow.message.ExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class NoResourceFoundExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(NoResourceFoundException e){
        ExceptionMessage message = new ExceptionMessage();
        message.setMessage("Not Found");
        message.setStatus(404);
        return ResponseEntity.status(404).body(message);
    }
}
