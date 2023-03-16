package com.example.schoolreyfowstudents.service;

import com.example.schoolreyfowstudents.aws.PublishTopic;
import com.example.schoolreyfowstudents.dto.StudentEventDTO;
import com.example.schoolreyfowstudents.dto.StudentFormDTO;
import com.example.schoolreyfowstudents.mapper.StudentMapper;
import com.example.schoolreyfowstudents.model.Student;
import com.example.schoolreyfowstudents.model.User;
import com.example.schoolreyfowstudents.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrchestratorStudentCreationFlowService {

    public static final Integer EVENT_STUDENT_CREATED = 10;
    private final StudentRepository studentRepository;
    private final StudentMapper mapper;
    private final PublishTopic publishTopic;
    private final CreateUserStudentService createUserStudentService;


    public String start(StudentFormDTO studentFormDTO) {
        final var student = mapper.studentFormDTOToStudent(studentFormDTO);
        studentRepository.save(student);
        final var user = createUserStudentService.create(student.getEmail());
        dispatcherStudentEvent(student, user);
        return student.getId();
    }



    private void dispatcherStudentEvent(Student student, User user) {
        final var event = StudentEventDTO
                .builder()
                .eventCode(EVENT_STUDENT_CREATED)
                .email(student.getEmail())
                .name(student.getName())
                .courseName(student.getName())
                .password(user.getPassword())
                .build();

        publishTopic.pubTopic(event);
    }
}