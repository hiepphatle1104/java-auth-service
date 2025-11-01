package com.swappie.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class TokenUtils {
    public static final long ACCESS_TOKEN_TTL = 2 * 60; // seconds
    public static final long REFRESH_TOKEN_TTL = 3 * 60;
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateToken() {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
