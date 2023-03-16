package com.example.schoolreyfowstudents.service;

import com.example.schoolreyfowstudents.dto.StudentDTO;
import com.example.schoolreyfowstudents.mapper.StudentMapper;
import com.example.schoolreyfowstudents.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FindStudentByIdService {

    private final StudentRepository studentRepository;

    private final StudentMapper mapper;

    public StudentDTO start(String id) {
        return studentRepository.findById(id)
                .map(mapper::studentToStudentDTO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, id));
    }
}
