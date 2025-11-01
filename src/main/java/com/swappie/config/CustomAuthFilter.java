package com.swappie.config;

import com.swappie.domain.AccessToken;
import com.swappie.repository.AccessTokenRepository;
import com.swappie.service.AccountService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().startsWith("/auth");
    }

    private final AccessTokenRepository accessTokenRepo;
    private final AccountService accountService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        final String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            log.debug("No Bearer token found in request");
            chain.doFilter(req, res);
            return;
        }

        final String token = header.substring(7);

        Optional<AccessToken> accessTokenOpt = accessTokenRepo.findByToken(token);

        if (accessTokenOpt.isEmpty()) {
            log.warn("Invalid or expired access token: {}", token);
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            return;
        }

        AccessToken accessToken = accessTokenOpt.get();
        String accountId = accessToken.getAccountId();

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = accountService.loadUserByUsername(accountId);

            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Authenticated user: {}", userDetails.getUsername());
        }

        chain.doFilter(req, res);
    }
}
