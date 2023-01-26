package com.example.schoolreyfowstudents.configs;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SNSConfig {

    @Value("${aws.local:false}")
    private boolean isLocal;

    @Value("${aws.endpoint:}")
    private String endpoint;

    @Value("${aws.region:}")
    private String region;

    @Bean
    public AmazonSNSClient getAmazonSnsClient() {
        if (isLocal) {
            return (AmazonSNSClient) AmazonSNSClientBuilder
                    .standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                    .build();

        }
        return (AmazonSNSClient) AmazonSNSClientBuilder.defaultClient();
    }

    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(final AmazonSNS amazonSNS) {
        return new NotificationMessagingTemplate(amazonSNS);
    }

}
