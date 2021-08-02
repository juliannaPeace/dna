package com.meli.test.dna.core.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueConstraintException extends RuntimeException {

    public UniqueConstraintException(String msg) {
        super(msg);
    }
}
