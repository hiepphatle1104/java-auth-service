package com.swappie.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionDTO {
    private String token;
    private LocalDateTime expiresAt;
}
