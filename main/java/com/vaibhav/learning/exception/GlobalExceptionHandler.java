package com.vaibhav.learning.exception;

import com.vaibhav.learning.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleEmployeNotFoundException(EmployeeNotFoundException exception, String message){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleEmployeNotFoundException(NoResourceFoundException exception, String message){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleEmployeNotFoundException(ResponseStatusException exception, String message){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}
