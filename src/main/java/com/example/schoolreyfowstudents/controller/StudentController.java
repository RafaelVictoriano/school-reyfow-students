package com.example.schoolreyfowstudents.controller;

import com.example.schoolreyfowstudents.service.StudentService;
import com.example.schoolreyfowstudents.dto.StudentDTO;
import com.example.schoolreyfowstudents.dto.StudentFormDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    //@RolesAllowed({STUDENT, TEACHER, COORDINATOR})
    @GetMapping("/{studentId}")
    private ResponseEntity<StudentDTO> get(@PathVariable String studentId) {
        var students = studentService.findStudentById(studentId);
        log.info("Consult with success");
        return ResponseEntity.ok(students);
    }


    //@RolesAllowed(COORDINATOR)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> post(@Valid @RequestBody StudentFormDTO studentFormDTO, UriComponentsBuilder uri) {
        log.info("Received request for created student, bodyRequest:{}", studentFormDTO);
        final var studentId = studentService.create(studentFormDTO);
        log.info("Student created with success, studentId:{}", studentId);
        final var uriStudentCreated = uri.path("/student/{studentId}").buildAndExpand(studentId).toUri();
        return ResponseEntity.created(uriStudentCreated).build();
    }


}
