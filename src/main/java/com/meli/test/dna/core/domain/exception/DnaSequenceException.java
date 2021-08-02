package com.meli.test.dna.core.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DnaSequenceException extends RuntimeException {

    public DnaSequenceException(String msg) {
        super(msg);
    }
}
