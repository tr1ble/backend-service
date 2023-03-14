package com.epam.learn.service.impl;

import com.epam.learn.model.Subscription;
import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import com.epam.learn.dto.subscription.SubscriptionResponseDto;
import com.epam.learn.mapper.SubscriptionMapper;
import com.epam.learn.repository.SubscriptionRepository;
import com.epam.learn.service.SubscriptionService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
  private final SubscriptionRepository subscriptionRepository;
  private final SubscriptionMapper subscriptionMapper;

  @Override
  public SubscriptionResponseDto create(SubscriptionRequestDto subscriptionRequest) {
    Subscription subscription = subscriptionMapper.mapRequestToDomain(subscriptionRequest);
    return subscriptionMapper.mapDomainToResponse(subscriptionRepository.save(subscription));
  }

  @Override
  public List<SubscriptionResponseDto> getAll() {
    Iterable<Subscription> users = subscriptionRepository.findAll();
    return StreamSupport
        .stream(users.spliterator(), false)
        .map(subscriptionMapper::mapDomainToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public SubscriptionResponseDto getById(UUID id) {
    Subscription subscription = subscriptionRepository.findById(id).orElseThrow(
        EntityNotFoundException::new);
    return subscriptionMapper.mapDomainToResponse(subscription);
  }

  @Override
  public void deleteById(UUID id) {
    subscriptionRepository.deleteById(id);
  }

  @Override
  public SubscriptionResponseDto update(SubscriptionRequestDto subscription) {
    Subscription subscriptionDto = subscriptionMapper.mapRequestToDomain(subscription);
    return subscriptionMapper.mapDomainToResponse(subscriptionRepository.save(subscriptionDto));
  }
}
