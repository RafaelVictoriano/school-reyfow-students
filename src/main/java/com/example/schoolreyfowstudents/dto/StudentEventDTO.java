package com.example.schoolreyfowstudents.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StudentEventDTO {

    private String studentName;
    private String studentEmail;
    private String courseName;
    private String password;


}
