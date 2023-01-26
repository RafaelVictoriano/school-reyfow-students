package com.example.schoolreyfowstudents.aws;


import com.amazonaws.services.sns.AmazonSNS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PublishTopic {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Value("${school-reyfow.topic-name}")
    private String topicName;

    public void pubTopic(Object student, Map<String, Object> header) {
        this.notificationMessagingTemplate.convertAndSend(topicName, student, header);
        log.info("Send message to topic, topicName:{}", topicName);
    }

}
