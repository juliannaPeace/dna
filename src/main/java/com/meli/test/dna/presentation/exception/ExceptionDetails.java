package com.meli.test.dna.presentation.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionDetails {
    private String title;
    private int status;
    private String details;
    private LocalDateTime timestamp;
}
