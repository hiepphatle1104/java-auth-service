package com.swappie.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Setter
    @Column(nullable = false, unique = true)
    private String username;

    @Setter
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Setter
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountRole role;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
