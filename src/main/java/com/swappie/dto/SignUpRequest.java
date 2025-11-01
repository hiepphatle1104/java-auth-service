package com.swappie.dto;

public record SignUpRequest(
        String username,
        String password,
        String confirmPassword
) {
}
