package com.example.schoolreyfowstudents.service;

import com.example.schoolreyfowstudents.model.User;
import com.example.schoolreyfowstudents.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.schoolreyfowstudents.enums.RoleName.ROLES_ESTUDANTE;

@Log4j2
@Service
public class CreateUserStudentService {

    @Autowired
    private UserRepository repository;

    public User create(final String username) {
        log.info("Creating user for student, username:{}", username);
        final var password = generatePassword();
        final var user = User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .role(ROLES_ESTUDANTE.getValue())
                .build();
        repository.save(user);
        user.setPassword(password);
        return user;
    }

    private String generatePassword() {
        final var pwdGenerator = new RandomStringGenerator.Builder()
                .withinRange(33, 45)
                .build();
        return pwdGenerator.generate(32);
    }
}
