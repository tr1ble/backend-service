package com.epam.learn.config;

import com.epam.learn.model.User;
import com.epam.learn.exception.KafkaErrorHandler;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
@EnableKafka
public class SchemaRegistryConfig {

  @Bean
  public ConsumerFactory<String, User> consumerFactory(KafkaProperties kafkaProperties) {
    return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, User>> kafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
    ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<String, User>();
    factory.setConsumerFactory(consumerFactory(kafkaProperties));
    return factory;
  }

  @Bean
  public CommonErrorHandler commonErrorHandler() {
    return new KafkaErrorHandler();
  }
}
