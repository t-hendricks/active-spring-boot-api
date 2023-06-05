package com.active.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.TOO_EARLY)
public class RepeatingException extends RuntimeException {
    public RepeatingException(String message) {
        super(message);
    }
}
