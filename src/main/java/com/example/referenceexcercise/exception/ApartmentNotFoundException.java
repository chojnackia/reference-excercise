package com.example.referenceexcercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ApartmentNotFoundException extends Throwable {
    public ApartmentNotFoundException(String message) {
        super(message);
    }
}
