package com.swappie.controller;

import com.swappie.dto.UserRegisterDTO;
import com.swappie.mapper.UserMapper;
import com.swappie.service.UserService;
import com.swappie.utils.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserMapper mapper;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO request) {
        var userId = userService.createUser(request);

        var res = ResponseFactory.created("user created", userId);
        return new ResponseEntity<>(res, res.getStatus());
    }
}
