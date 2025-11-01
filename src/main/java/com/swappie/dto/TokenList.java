package com.swappie.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record TokenList(
    @JsonIgnore
    String refreshToken,
    String accessToken
) {
}
