package com.epam.learn.util;

import com.epam.learn.dto.subscription.SubscriptionDto;
import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubscriptionUtils {

  public static List<SubscriptionDto> generateNewSubscriptions(int number) {
    List<SubscriptionDto> subscriptions = new ArrayList<>(number);
    while (number > 0) {
      subscriptions.add(SubscriptionDto
          .builder()
          .user(UserUtils.generateNewUser())
          .build());
      number--;
    }
    return subscriptions;
  }

  public static SubscriptionDto generateNewSubscription() {
    return SubscriptionDto.builder()
        .user(UserUtils.generateNewUser())
        .build();
  }

  public static SubscriptionRequestDto generateSubscriptionRequest(UUID userId) {
    return SubscriptionRequestDto.builder()
        .userId(userId)
        .build();
  }
}
