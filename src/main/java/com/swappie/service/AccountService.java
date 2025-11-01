package com.swappie.service;

import com.swappie.domain.Account;
import com.swappie.domain.AccountRole;
import com.swappie.dto.AccountUpdateRequest;
import com.swappie.dto.SignUpRequest;
import com.swappie.exception.InvalidException;
import com.swappie.exception.NotFoundException;
import com.swappie.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository repo;

    private final PasswordEncoder passwordEncoder;

    public Account authenticate(String username, String password) {
        Account account = getAccountByUsername(username);

        if (!passwordEncoder.matches(password, account.getPasswordHash()))
            throw new InvalidException("invalid credential", HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIAL");

        return account;
    }

    public Account getAccountByUsername(String username) {
        Optional<Account> result = repo.findByUsername(username);
        if (result.isEmpty())
            throw new NotFoundException("account not found", "ACCOUNT_NOT_FOUND");

        return result.get();
    }

    public Account createAccount(SignUpRequest req) {
        Account account = Account.builder()
            .username(req.username())
            .passwordHash(passwordEncoder.encode(req.password()))
            .role(req.username().equals("admin") ? AccountRole.ADMIN : AccountRole.USER)
            .build();

        return repo.save(account);
    }

    public Account getAccountById(String id) {
        Optional<Account> result = repo.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("account not found", "ACCOUNT_NOT_FOUND");

        return result.get();
    }

    public void updateAccount(String id, AccountUpdateRequest req) {
        // NOTES: Maybe another way to merge
        Account account = getAccountById(id);

        account.setUsername(req.username());
        account.setEmail(req.email());
        account.setAddress(req.address());

        repo.save(account);
    }

    public void deleteAccount(String id) {
        repo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Account account = repo.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User
            .withUsername(account.getUsername())
            .password(account.getPasswordHash())
            .authorities("ROLE_" + account.getRole().name())
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build();
    }
}
