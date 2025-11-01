package com.swappie.dto;

public record AccountUpdateRequest(
    String username,
    String email,
    String address
) {
}
