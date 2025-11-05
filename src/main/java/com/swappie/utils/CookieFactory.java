package com.swappie.utils;

import org.springframework.http.ResponseCookie;

public class CookieFactory {
    public static ResponseCookie createCookie(String name, String value) {
        return ResponseCookie
                .from(name)
                .value(value)
                .httpOnly(true)
                .secure(false)
                .build();

    }
}
