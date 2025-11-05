package com.swappie.controller;

import com.swappie.domain.ApiUserDetails;
import com.swappie.dto.UserLoginDTO;
import com.swappie.dto.UserRegisterDTO;
import com.swappie.exception.MissingException;
import com.swappie.service.SessionService;
import com.swappie.service.UserService;
import com.swappie.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final SessionService sessionService;
    private final AuthenticationManager manager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto) {
        var userId = userService.createUser(dto);

        var res = ResponseFactory.created("user created", userId);
        return new ResponseEntity<>(res, res.getStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto, HttpServletRequest request) throws NoSuchAlgorithmException {
        var userAgent = request.getHeader("User-Agent");
        if (userAgent == null)
            throw new MissingException("User-Agent header is missing", "MISSING_USER_AGENT");

        var authentication = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var result = (ApiUserDetails) manager.authenticate(authentication).getPrincipal();

        var user = result.getUser();
        var token = sessionService.createSession(user, userAgent);

//        var resCookie = CookieFactory.createCookie("sid", token);
        var res = ResponseFactory.ok("user authenticated", token);

        return ResponseEntity
                .status(res.getStatus())
//                .header(HttpHeaders.SET_COOKIE, resCookie.toString())
                .body(res);
    }
}
