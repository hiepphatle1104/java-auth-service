package com.swappie.config;

import com.swappie.domain.ApiUserDetails;
import com.swappie.domain.entities.Session;
import com.swappie.domain.entities.User;
import com.swappie.service.SessionService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthFilter extends OncePerRequestFilter {
    private final SessionService sessionService;

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        var routes = List.of("/api/auth/login", "/api/auth/register", "/h2-console");

        return routes.stream().anyMatch(route -> request.getRequestURI().startsWith(route));
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        IO.println("header: " + header);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        IO.println("token: " + token);

        // Check access_token here and get userid
        Session session = sessionService.getSessionByToken(token);
        if (session == null) {
            log.warn("session is null");
            filterChain.doFilter(request, response);
            return;
        }

        // Find user by userid
        User user = session.getUser();
        if (user == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            log.warn("user is null or already logged in");
            filterChain.doFilter(request, response);
            return;
        }

        var userDetails = new ApiUserDetails(user);
        var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("Authentication Success");
        filterChain.doFilter(request, response);
    }
}
