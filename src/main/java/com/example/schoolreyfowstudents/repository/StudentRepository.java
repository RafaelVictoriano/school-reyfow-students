package com.example.schoolreyfowstudents.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.schoolreyfowstudents.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class StudentRepository  {

    private final DynamoDBMapper dynamoDBMapper;

    public void save(Student student) {
        dynamoDBMapper.save(student);
    }

    public Optional<Student> findById(String id) {
       return Optional.ofNullable(dynamoDBMapper.load(Student.class, id));
    }
}