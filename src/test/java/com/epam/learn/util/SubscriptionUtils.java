package com.epam.learn.util;

import com.epam.learn.model.Subscription;
import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubscriptionUtils {

  public static List<Subscription> generateNewSubscriptions(int number) {
    List<Subscription> subscriptions = new ArrayList<>(number);
    while (number > 0) {
      subscriptions.add(Subscription
          .builder()
          .user(UserUtils.generateNewUser())
          .build());
      number--;
    }
    return subscriptions;
  }

  public static Subscription generateNewSubscription() {
    return Subscription.builder()
        .user(UserUtils.generateNewUser())
        .build();
  }

  public static SubscriptionRequestDto generateSubscriptionRequest(UUID userId) {
    return SubscriptionRequestDto.builder()
        .userId(userId)
        .build();
  }
}
