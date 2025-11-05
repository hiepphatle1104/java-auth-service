package com.swappie.service;

import com.swappie.domain.entities.Session;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {
    Session createSession(Session session);

    Session getSessionById(String id);

    void deleteSessionById(String id);

    void updateSessionById(Session session);
}
