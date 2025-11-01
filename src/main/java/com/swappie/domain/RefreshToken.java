package com.swappie.domain;

import com.swappie.utils.TokenUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JoinColumn(name = "account_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Account account;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiresIn;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean revoked = false;

    @PrePersist
    public void prePersist() {
        var now = LocalDateTime.now();
        this.expiresIn = now.plusSeconds(TokenUtils.REFRESH_TOKEN_TTL);
        this.createdAt = now;
    }
}
