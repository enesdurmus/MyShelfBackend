package com.enesd.myshelfbackend.model.exceptions;

public class TooManyRequestException extends RuntimeException {
    public TooManyRequestException(String message) {
        super(message);
    }
}
