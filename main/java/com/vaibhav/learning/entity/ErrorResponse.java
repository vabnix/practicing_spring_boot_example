package com.vaibhav.learning.entity;

import java.time.LocalDateTime;

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

