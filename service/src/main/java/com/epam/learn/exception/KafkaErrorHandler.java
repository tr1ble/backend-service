package com.epam.learn.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

@Slf4j
public class KafkaErrorHandler implements CommonErrorHandler {

  @Override
  public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record,
      Consumer<?, ?> consumer, MessageListenerContainer container) {
    log.error(thrownException.getMessage());
    return CommonErrorHandler.super.handleOne(thrownException, record, consumer, container);
  }
}
