package com.example.schoolreyfowstudents.service;

import com.example.schoolreyfowstudents.enums.RoleName;
import com.example.schoolreyfowstudents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));

        return new User(user.getUsername(), user.getPassword(), List.of(RoleName.getRole(user.getRole())));
    }

}
