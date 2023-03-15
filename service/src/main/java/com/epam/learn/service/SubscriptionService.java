package com.epam.learn.service;

import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import com.epam.learn.dto.subscription.SubscriptionResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SubscriptionService {
  SubscriptionResponseDto create(SubscriptionRequestDto subscription);
  List<SubscriptionResponseDto> getAll();
  SubscriptionResponseDto getById(UUID id);
  void deleteById(UUID id);
  SubscriptionResponseDto update(SubscriptionRequestDto subscription);
}
