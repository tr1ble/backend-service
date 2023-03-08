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
//  @Value("${spring.cloud.stream.kafka.binder.producer-properties.schema.registry.url}")
//  private String endPoint;
//
//  @Bean
//  public SchemaRegistryClient schemaRegistryClient() {
//    ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
//    client.setEndpoint(endPoint);
//    return client;
//  }
//
//  @Bean
//  public MessageConverter userMessageConverter() {
//    return new AvroSchemaMessageConverter(MimeType.valueOf("application/avro"));
//  }

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
