package com.swappie.dto;

public record SignInRequest(
        String username,
        String password
) {
}
