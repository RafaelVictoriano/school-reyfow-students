package com.example.schoolreyfowstudents.controller;

import com.example.schoolreyfowstudents.service.FindStudentByIdService;
import com.example.schoolreyfowstudents.service.OrchestratorStudentCreationFlowService;
import com.example.schoolreyfowstudents.dto.StudentDTO;
import com.example.schoolreyfowstudents.dto.StudentFormDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final OrchestratorStudentCreationFlowService orchestratorStudentCreationFlowService;
    private final FindStudentByIdService findStudentById;

    @RolesAllowed({"ROLES_CORDENADOR", "ROLES_PROFESSOR", "ROLES_ESTUDANTE"})
    @GetMapping("/{studentId}")
    private ResponseEntity<StudentDTO> get(@PathVariable String studentId) {
        var students = findStudentById.start(studentId);
        log.info("Consult with success");
        return ResponseEntity.ok(students);
    }


    @RolesAllowed("ROLES_CORDENADOR")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> post(@Valid @RequestBody StudentFormDTO studentFormDTO, UriComponentsBuilder uri) {
        log.info("Received request for created student, bodyRequest:{}", studentFormDTO);
        final var studentId = orchestratorStudentCreationFlowService.start(studentFormDTO);
        log.info("Student created with success, studentId:{}", studentId);
        final var uriStudentCreated = uri.path("/student/{studentId}").buildAndExpand(studentId).toUri();
        return ResponseEntity.created(uriStudentCreated).build();
    }


}
