package com.swappie.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
    private final String errorCode;

    @JsonIgnore
    private final HttpStatus status;

    public BaseException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
