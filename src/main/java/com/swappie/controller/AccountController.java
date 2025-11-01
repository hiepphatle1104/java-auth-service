package com.swappie.controller;

import com.swappie.domain.Account;
import com.swappie.dto.AccountUpdateRequest;
import com.swappie.dto.ResponseFactory;
import com.swappie.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable String id) {
        Account account = accountService.getAccountById(id);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseFactory.success("account found", account));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseFactory.success("account deleted"));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateAccount(@PathVariable String id, @RequestBody AccountUpdateRequest req) {
        accountService.updateAccount(id, req);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseFactory.success("account updated"));
    }
}
