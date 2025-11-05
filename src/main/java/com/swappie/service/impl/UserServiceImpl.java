package com.swappie.service.impl;

import com.swappie.domain.entities.User;
import com.swappie.dto.UserDTO;
import com.swappie.dto.UserRegisterDTO;
import com.swappie.exception.NotFoundException;
import com.swappie.mapper.UserMapper;
import com.swappie.repository.UserRepository;
import com.swappie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final UserMapper mapper;

    @Override
    public UUID createUser(UserRegisterDTO request) {
        User saved = repo.save(mapper.toEntity(request));
        return saved.getId();
    }

    @Override
    public UserDTO getUserById(UUID id) {
        Optional<User> result = repo.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("user not found", "USER_NOT_FOUND");

        return mapper.toDTO(result.get());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = repo.findAll();
        if (users.isEmpty())
            throw new NotFoundException("users not found", "USERS_NOT_FOUND");

        return users.stream().map(mapper::toDTO).toList();
    }

    @Override
    public void deleteUserById(UUID id) {
        if (!repo.existsById(id))
            throw new NotFoundException("user not found", "USER_NOT_FOUND");
        repo.deleteById(id);
    }

    @Override
    public void updateUserById(UUID id, UserDTO userDto) {
        Optional<User> result = repo.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("user not found", "USER_NOT_FOUND");

        User exist = result.get();
        mapper.updateUserFromDto(userDto, exist);
        repo.save(exist);
    }
}
