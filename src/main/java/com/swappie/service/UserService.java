package com.swappie.service;

import com.swappie.dto.UserDTO;
import com.swappie.dto.UserRegisterDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    UUID createUser(UserRegisterDTO dto);

    UserDTO getUserById(UUID id);

    // Admin only
    List<UserDTO> getAllUsers();

    void deleteUserById(UUID id);

    void updateUserById(UUID id, UserDTO dto);
}
