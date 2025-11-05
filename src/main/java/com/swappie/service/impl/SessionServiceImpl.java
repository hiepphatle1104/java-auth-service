package com.swappie.service.impl;

import com.swappie.domain.entities.Session;
import com.swappie.domain.entities.User;
import com.swappie.repository.SessionRepository;
import com.swappie.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository repo;

    @Override
    public String createSession(User user, String userAgent) {
        var token = UUID.randomUUID().toString();
        repo.save(Session.builder()
                .user(user)
                .token(token)
                .userAgent(userAgent)
                .build()
        );

        return token;
    }

    @Override
    public Session getSessionById(String id) {
        return null;
    }

    @Override
    public void deleteSessionById(String id) {

    }

    @Override
    public void updateSessionById(Session session) {

    }

    @Override
    public Session getSessionByToken(String token) {
        Optional<Session> result = repo.findSessionByToken(token);

        return result.orElse(null);

    }
}
