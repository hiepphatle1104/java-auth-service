package com.swappie.utils;

import org.springframework.http.HttpStatus;

public class ResponseFactory {
    private static <T> Response<T> base(String message, HttpStatus status, String errorCode, T data) {
        return Response.<T>builder()
                .message(message)
                .status(status)
                .errorCode(errorCode)
                .data(data)
                .build();
    }

    public static <T> Response<T> ok(String message, T data) {
        return base(message, HttpStatus.OK, null, data);
    }

    public static <T> Response<T> ok(String message) {
        return base(message, HttpStatus.OK, null, null);
    }

    public static <T> Response<T> badRequest(String message, T data, String errorCode) {
        return base(message, HttpStatus.BAD_REQUEST, errorCode, data);
    }

    public static <T> Response<T> badRequest(String message, String errorCode) {
        return base(message, HttpStatus.BAD_REQUEST, errorCode, null);
    }

    public static <T> Response<T> created(String message, T data) {
        return base(message, HttpStatus.CREATED, null, data);
    }

    public static <T> Response<T> created(String message) {
        return base(message, HttpStatus.CREATED, null, null);
    }

    public static <T> Response<T> error(String message, HttpStatus status, String errorCode) {
        return base(message, status, errorCode, null);
    }

    public static <T> Response<T> internalError(String message) {
        return base(message, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", null);
    }
}
