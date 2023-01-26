package com.example.schoolreyfowstudents.mapper;


import com.example.schoolreyfowstudents.dto.StudentDTO;
import com.example.schoolreyfowstudents.dto.StudentFormDTO;
import com.example.schoolreyfowstudents.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    @Mapping(target = "active", ignore = true)
    public abstract Student studentFormDTOToStudent(StudentFormDTO studentFormDTO);

    public abstract StudentDTO studentToStudentDTO(Student student);

}
