package com.example.schoolreyfowstudents.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.schoolreyfowstudents.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public void save(User user) {
        dynamoDBMapper.save(user);
    }

}