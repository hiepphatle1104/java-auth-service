package com.swappie.controller;

import com.swappie.domain.Account;
import com.swappie.dto.ResponseFactory;
import com.swappie.dto.SignInRequest;
import com.swappie.dto.SignUpRequest;
import com.swappie.dto.TokenList;
import com.swappie.service.AccountService;
import com.swappie.service.TokenService;
import com.swappie.utils.CookieUtils;
import com.swappie.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest req) {
        Account savedAccount = accountService.createAccount(req);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ResponseFactory.success("account created", savedAccount.getId()));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest req) {
        var credential = new UsernamePasswordAuthenticationToken(req.username(), req.password());
        var authentication = manager.authenticate(credential);

        Account account = (Account) authentication.getPrincipal();

        TokenList tokenList = tokenService.generateAndSaveToken(account);
        var cookie = CookieUtils.createCookie("refresh_token", tokenList.refreshToken(), TokenUtils.REFRESH_TOKEN_TTL);

        return ResponseFactory.okWithCookie("authenticated", cookie, tokenList);
    }
}
