package com.example.myscram.config;

import com.example.myscram.model.CredentialsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private final UserAuthenticationProvider provider;

    public UsernamePasswordAuthFilter(UserAuthenticationProvider provider) {
        this.provider = provider;
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if ("v1/signIn".equals(request.getServletPath())
            && HttpMethod.POST.matches(request.getMethod())) {
            CredentialsDto credentialsDto = MAPPER.readValue(request.getInputStream(), CredentialsDto.class);

            try {
                SecurityContextHolder.getContext().setAuthentication(
                        provider.validateCredentials(credentialsDto)
                );
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }
        filterChain.doFilter(request, response);
    }
}
