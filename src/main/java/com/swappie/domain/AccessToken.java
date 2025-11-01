package com.swappie.domain;

import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash("access_tokens")
@Data
@Builder
public class AccessToken implements Serializable {
    @Id
    private String token;

    private String accountId;
    private LocalDateTime createdAt;

    @TimeToLive
    private long expiresIn; // seconds

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
