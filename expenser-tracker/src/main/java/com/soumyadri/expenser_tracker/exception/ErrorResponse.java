package com.soumyadri.expenser_tracker.exception;

import java.time.LocalDateTime;

public record ErrorResponse (
    int status,
    String error,
    String message,
    LocalDateTime timestamp
) {}