package com.swappie.exception;

import org.springframework.http.HttpStatus;

public class MissingException extends BaseException {
    public MissingException(String message, String errorCode) {
        super(message, HttpStatus.BAD_GATEWAY, errorCode);
    }
}
