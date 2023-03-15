package com.epam.learn.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.learn.model.Subscription;
import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import com.epam.learn.dto.subscription.SubscriptionResponseDto;
import com.epam.learn.mapper.SubscriptionMapper;
import com.epam.learn.repository.SubscriptionRepository;
import com.epam.learn.service.impl.SubscriptionServiceImpl;
import com.epam.learn.util.SubscriptionUtils;
import java.util.UUID;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTest extends TestCase {
  @InjectMocks
  SubscriptionServiceImpl subscriptionService;

  @Mock
  SubscriptionMapper mapper;

  @Mock
  SubscriptionRepository repository;

  @Test
  public void testCreate() {
    SubscriptionRequestDto request = SubscriptionUtils.generateSubscriptionRequest();
    SubscriptionResponseDto response = SubscriptionUtils.generateSubscriptionResponse();
    Subscription subscription = SubscriptionUtils.generateNewSubscription();
    when(repository.save(subscription)).thenReturn(subscription);
    when(mapper.mapRequestToDomain(request)).thenReturn(subscription);
    when(mapper.mapDomainToResponse(subscription)).thenReturn(response);

    UUID actual = subscriptionService.create(request).getId();

    verify(repository).save(subscription);

    assertThat(actual).isEqualTo(subscription.getId());
  }
}
