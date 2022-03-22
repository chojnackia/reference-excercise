package com.example.referenceexcercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TenantNotFoundException extends Throwable {
    public TenantNotFoundException(String message) {
        super(message);
    }
}
