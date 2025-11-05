package com.swappie.service.impl;

import com.swappie.domain.entities.User;
import com.swappie.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String createUser(User user) {
        return "";
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public void deleteUserById(String id) {

    }

    @Override
    public void updateUserById(User user) {

    }
}
