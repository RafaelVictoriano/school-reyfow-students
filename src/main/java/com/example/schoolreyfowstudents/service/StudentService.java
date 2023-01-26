package com.example.schoolreyfowstudents.service;

import com.example.schoolreyfowstudents.aws.PublishTopic;
import com.example.schoolreyfowstudents.dto.StudentDTO;
import com.example.schoolreyfowstudents.dto.StudentFormDTO;
import com.example.schoolreyfowstudents.mapper.StudentMapper;
import com.example.schoolreyfowstudents.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@Service
public class StudentService {

    public static final String FILTER = "filter";
    public static final String STUDENT_CREATED = "STUDENT_CREATED";
    private final StudentRepository studentRepository;
    private final StudentMapper mapper;
    private final PublishTopic publishTopic;


    public String create(StudentFormDTO studentFormDTO) {
        var student = mapper.studentFormDTOToStudent(studentFormDTO);
        studentRepository.save(student);
        publishTopic.pubTopic(student, Map.of(FILTER, STUDENT_CREATED));
        return student.getId();
    }

    public StudentDTO findStudentById(String id) {
        return studentRepository.findById(id)
                .map(mapper::studentToStudentDTO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,id));
    }

}
