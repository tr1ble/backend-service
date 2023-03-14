package com.epam.learn.service;

import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import com.epam.learn.dto.subscription.SubscriptionResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

public interface SubscriptionService {
  @Transactional
  SubscriptionResponseDto create(SubscriptionRequestDto subscription);
  @Transactional
  List<SubscriptionResponseDto> getAll();
  @Transactional
  SubscriptionResponseDto getById(UUID id);
  @Transactional
  void deleteById(UUID id);
  @Transactional
  SubscriptionResponseDto update(SubscriptionRequestDto subscription);
}
