package com.example.schoolreyfowstudents.service;

import com.example.schoolreyfowstudents.aws.PublishTopic;
import com.example.schoolreyfowstudents.dto.StudentDTO;
import com.example.schoolreyfowstudents.dto.StudentEventDTO;
import com.example.schoolreyfowstudents.dto.StudentFormDTO;
import com.example.schoolreyfowstudents.mapper.StudentMapper;
import com.example.schoolreyfowstudents.model.Student;
import com.example.schoolreyfowstudents.model.User;
import com.example.schoolreyfowstudents.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@Service
public class StudentService {

    public static final String FILTER = "EVENT_NAME";
    public static final String STUDENT_CREATED = "STUDENT_CREATED";
    private final StudentRepository studentRepository;
    private final StudentMapper mapper;
    private final PublishTopic publishTopic;

    private final CreateUserStudentService createUserStudentService;


    public String create(StudentFormDTO studentFormDTO) {
        final var student = mapper.studentFormDTOToStudent(studentFormDTO);
        studentRepository.save(student);
        final var user = createUserStudentService.create(student.getEmail());
        dispatcherStudentEvent(student, user);
        return student.getId();
    }

    public StudentDTO findStudentById(String id) {
        return studentRepository.findById(id)
                .map(mapper::studentToStudentDTO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, id));
    }

    private void dispatcherStudentEvent(Student student, User user) {
        final var event = StudentEventDTO
                .builder()
                .email(student.getEmail())
                .name(student.getName())
                .courseName(student.getName())
                .password(user.getPassword())
                .build();

        publishTopic.pubTopic(event, Map.of(FILTER, STUDENT_CREATED));
    }
}
