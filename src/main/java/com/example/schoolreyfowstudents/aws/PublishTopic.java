package com.example.schoolreyfowstudents.aws;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PublishTopic {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Value("${school-reyfow.topic-name}")
    private String topicName;

    public void pubTopic(Object event) {
        this.notificationMessagingTemplate.convertAndSend(topicName, event);
        log.info("Send message to topic, topicName:{}", topicName);
    }


    public void pubTopic(Object event, Map<String, Object> headers) {
        this.notificationMessagingTemplate.convertAndSend(topicName, event, headers);
        log.info("Send message to topic, topicName:{}", topicName);
    }

}
