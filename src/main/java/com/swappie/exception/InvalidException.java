package com.swappie.exception;

import org.springframework.http.HttpStatus;

public class InvalidException extends BaseException {
    public InvalidException(String message, HttpStatus status, String errorCode) {
        super(message, status, errorCode);
    }
}
