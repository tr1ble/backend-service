package com.epam.learn.backendservice.messaging.consumer;

import com.epam.learn.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserConsumer {

  @KafkaListener(topics = "${spring.kafka.properties.topic.name}", containerFactory = "kafkaListenerContainerFactory", groupId = "${spring.kafka.consumer.group-id}")
  public void consumeUser(ConsumerRecord<String, User> user) {
    log.info("User: {}", user.value());
  }
}
