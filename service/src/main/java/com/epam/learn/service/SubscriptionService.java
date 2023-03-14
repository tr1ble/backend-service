package com.epam.learn.service;

import com.epam.learn.dto.subscription.SubscriptionDto;
import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import com.epam.learn.dto.subscription.SubscriptionResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface SubscriptionService {
  @Transactional(isolation = Isolation.SERIALIZABLE)
  SubscriptionResponseDto create(SubscriptionRequestDto subscription);
  @Transactional(isolation = Isolation.READ_COMMITTED)
  List<SubscriptionResponseDto> getAll();
  @Transactional(isolation = Isolation.READ_COMMITTED)
  SubscriptionResponseDto getById(UUID id);
  @Transactional(isolation = Isolation.SERIALIZABLE)
  void deleteById(UUID id);
  @Transactional(isolation = Isolation.SERIALIZABLE)
  SubscriptionResponseDto update(SubscriptionRequestDto subscription);
}
