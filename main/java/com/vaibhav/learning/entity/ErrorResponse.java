package com.vaibhav.learning.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private LocalDateTime dateTime;
    private String message;
    private String details;

    public ErrorResponse(LocalDateTime dateTime, String message, String details) {
        this.dateTime = dateTime;
        this.message = message;
        this.details = details;
    }
}

