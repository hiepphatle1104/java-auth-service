package com.swappie.service;

import com.swappie.domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    String createUser(User user);

    User getUserById(String id);

    // Admin only
    List<User> getAllUsers();

    void deleteUserById(String id);

    void updateUserById(User user);
}
