package com.example.schoolreyfowstudents.util;

import com.example.schoolreyfowstudents.repository.UserRepository;
import com.example.schoolreyfowstudents.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserDetailsServiceImpl service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final var header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final var token = header.substring(7);
        if (!jwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);

            return;
        }

        var userDetails = service.loadUserByUsername(jwtTokenUtil.getUsername(token));
        var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, ofNullable(userDetails)
                .map(UserDetails::getAuthorities).orElse(null));

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}
