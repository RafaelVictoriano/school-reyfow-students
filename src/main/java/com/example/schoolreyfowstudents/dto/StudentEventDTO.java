package com.example.schoolreyfowstudents.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class StudentEventDTO {

    private Integer eventCode;
    private String name;
    private String email;
    private String courseName;
    private String password;


}
