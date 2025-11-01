package com.swappie.dto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
            .message(message)
            .data(data)
            .build();
    }

    public static <T> ApiResponse<T> success(String message) {
        return success(message, null);
    }

    public static <T> ResponseEntity<?> okWithData(String message, T data) {
        var resp = ApiResponse.<T>builder()
            .message(message)
            .data(data)
            .build();

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(resp);
    }

    public static ResponseEntity<?> ok(String message) {
        return okWithData(message, null);
    }

    public static <T> ResponseEntity<?> okWithCookie(String message, ResponseCookie cookie, T data) {
        var resp = ApiResponse.<T>builder()
            .message(message)
            .data(data)
            .build();

        return ResponseEntity
            .status(HttpStatus.OK)
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(resp);
    }
}
