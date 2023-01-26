package com.example.schoolreyfowstudents.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentFormDTO {

    @NotEmpty
    private String name;
    @Email(message = "Email is invalid")
    private String email;
    @NotNull
    @Min(10) @Max(18)
    private Integer age;
    @NotNull @Min(1)
    private Long phone;
    @NotNull
    private String courseId;
}

