package com.epam.learn.backendservice.config;

import com.epam.learn.backendservice.model.UserModel;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class SchemaRegistryConfig {

  @Bean
  public ConsumerFactory<String, UserModel> consumerFactory(KafkaProperties kafkaProperties) {
    return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, UserModel>> kafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
    ConcurrentKafkaListenerContainerFactory<String, UserModel> factory = new ConcurrentKafkaListenerContainerFactory<String, UserModel>();
    factory.setConsumerFactory(consumerFactory(kafkaProperties));
    return factory;
  }
}
