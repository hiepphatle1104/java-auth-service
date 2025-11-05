package com.swappie.config;

import com.swappie.domain.ApiUserDetails;
import com.swappie.domain.entities.User;
import com.swappie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomAuthProvider implements AuthenticationProvider {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> result = repo.findByEmail(email);
        if (result.isEmpty())
            throw new BadCredentialsException("invalid credentials");

        User user = result.get();
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("invalid credentials");

        var userDetails = new ApiUserDetails(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
