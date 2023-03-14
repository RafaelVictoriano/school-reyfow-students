package com.example.schoolreyfowstudents.enums;

import com.example.schoolreyfowstudents.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
@ToString
public enum RoleName {

    ROLES_CORDENADOR("ROLES_CORDENADOR"),
    ROLES_PROFESSOR("ROLES_PROFESSOR"),
    ROLES_ESTUDANTE("ROLES_ESTUDANTE");

    public static Role getRole(String role) {
        final var roles = Arrays.stream(RoleName.values())
                .filter(roleName -> role.equals(roleName.getValue()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Não autorizado"));
        return new Role(roles);
    }

    public final String value;

}
