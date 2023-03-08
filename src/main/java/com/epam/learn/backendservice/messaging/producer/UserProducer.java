package com.epam.learn.backendservice.messaging.producer;

import com.epam.learn.User;;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProducer {
  @Value("${spring.kafka.properties.topic.name}")
  private String topicName;
  private final KafkaTemplate<String, User> template;

  public void produceUser(User user) {
    template.send(topicName, String.valueOf(user.getId()), user);
  }
}
