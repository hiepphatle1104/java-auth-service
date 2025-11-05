package com.swappie.exception;

import com.swappie.utils.ResponseFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<?> handleBaseException(BaseException e) {
        var res = ResponseFactory.error(e.getMessage(), e.getStatus(), e.getErrorCode());
        return new ResponseEntity<>(res, res.getStatus());
    }
}
