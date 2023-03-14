package com.example.schoolreyfowstudents.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "user")
public class User {

    @DynamoDBHashKey
    private String username;

    @DynamoDBAttribute
    private String password;

    @DynamoDBAttribute
    private String role;


}