package com.swappie.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<?> handleBaseException(BaseException e) {

        return ResponseEntity.status(e.getStatus()).body(Map.of(
            "message", e.getMessage(),
            "error_code", e.getErrorCode()
        ));
    }

    @ExceptionHandler(value = NoSuchAlgorithmException.class)
    public ResponseEntity<?> handleNoSuchAlgorithmException(NoSuchAlgorithmException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
            "message", e.getMessage()
        ));
    }

}
