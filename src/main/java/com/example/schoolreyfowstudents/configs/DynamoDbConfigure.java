package com.example.schoolreyfowstudents.configs;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfigure {

    @Value("${aws.local:false}")
    private boolean isLocal;

    @Value("${aws.endpoint:}")
    private String endpoint;

    @Value("${aws.region:}")
    private String region;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        if (isLocal) {
            return new DynamoDBMapper(AmazonDynamoDBClientBuilder
                    .standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                    .build());
        }
        return new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());
    }
}
