package com.swappie.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex) throws IOException, ServletException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        mapper.writeValue(res.getWriter(), Map.of("message", "please login to continue"));
    }
}
