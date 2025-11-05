package com.swappie.service;

import com.swappie.domain.entities.Session;
import com.swappie.domain.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {
    String createSession(User user, String userAgent);

    Session getSessionById(String id);

    void deleteSessionById(String id);

    void updateSessionById(Session session);

    Session getSessionByToken(String token);

    void deleteSessionByUser(User user);
}
