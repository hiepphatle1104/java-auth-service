package com.swappie.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieUtils {
    public static ResponseCookie createCookie(String name, String value, long maxAge) {
        return ResponseCookie
            .from(name)
            .value(value)
            .httpOnly(true)
            .secure(false)
            .maxAge(maxAge)
            .path("/") // NOTES: Maybe set to using /api/**
            .build();
    }
}
