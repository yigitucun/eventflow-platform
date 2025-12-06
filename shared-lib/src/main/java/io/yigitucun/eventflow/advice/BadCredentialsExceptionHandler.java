package io.yigitucun.eventflow.advice;

import io.yigitucun.eventflow.message.ExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class BadCredentialsExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleBadCredentialsException(BadCredentialsException exceptionHandler){
        ExceptionMessage message = new ExceptionMessage();
        message.setMessage("Username or password wrong.");
        message.setStatus(403);
        return ResponseEntity.status(403).body(message);

    }

}
