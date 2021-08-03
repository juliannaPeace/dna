package com.meli.test.dna.presentation.exception;

import lombok.Data;

@Data
public class ValidationExceptionDetails extends ExceptionDetails {
    private String fields;
    private String fieldsMessage;
}
