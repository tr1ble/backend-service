package com.epam.learn.util;

import com.epam.learn.dto.subscription.SubscriptionDto;
import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import com.epam.learn.dto.subscription.SubscriptionResponseDto;
import com.epam.learn.dto.user.UserDto;
import java.util.UUID;

public class SubscriptionUtils {
  private final static UUID id = UUID.randomUUID();

  SubscriptionUtils () {

  }
  public static SubscriptionDto generateNewSubscription() {
    return SubscriptionDto.builder()
        .user(UserDto.builder().build())
        .build();
  }

  public static SubscriptionRequestDto generateSubscriptionRequest() {
    return SubscriptionRequestDto.builder()
        .userId(id)
        .build();
  }

  public static SubscriptionResponseDto generateSubscriptionResponse() {
    return SubscriptionResponseDto.builder()
        .userId(id)
        .build();
  }
}
