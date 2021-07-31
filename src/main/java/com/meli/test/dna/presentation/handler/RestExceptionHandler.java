package com.meli.test.dna.presentation.handler;

import com.meli.test.dna.presentation.exception.ValidationExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        var validationExceptionDetails = new ValidationExceptionDetails();
        validationExceptionDetails.setTimestamp(LocalDateTime.now());
        validationExceptionDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        validationExceptionDetails.setTitle("Bad Request Exception, Invalid Fields");
        validationExceptionDetails.setDetails("Check the field(s) error");
        validationExceptionDetails.setFields(fields);
        validationExceptionDetails.setFieldsMessage(fieldsMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationExceptionDetails);
    }
}
