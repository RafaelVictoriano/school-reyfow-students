package com.example.schoolreyfowstudents.model;

import com.example.schoolreyfowstudents.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements GrantedAuthority {
    private RoleName value;


    @Override
    public String getAuthority() {
        return value.toString();
    }
}
