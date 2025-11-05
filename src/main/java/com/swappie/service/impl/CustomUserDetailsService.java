package com.swappie.service.impl;

import com.swappie.domain.ApiUserDetails;
import com.swappie.domain.entities.User;
import com.swappie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> result = repo.findByEmail(email);
        if (result.isEmpty())
            throw new UsernameNotFoundException("user not found with email: " + email);

        return new ApiUserDetails(result.get());
    }
}
