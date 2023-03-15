package com.epam.learn.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import com.epam.learn.dto.subscription.SubscriptionResponseDto;
import com.epam.learn.dto.user.UserResponseDto;
import com.epam.learn.util.SubscriptionUtils;
import com.epam.learn.util.UserUtils;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("DEV")
public class SubscriptionApiTest {

  @Autowired
  private transient SubscriptionApi subscriptionApi;
  @Autowired
  private transient UserApi userApi;

  @Test
  public void saveSubscriptionTestWithNotUser_shouldCreateSubscription() {
    UserResponseDto userResponseDto = userApi.create(UserUtils.generateUserRequest());
    SubscriptionRequestDto request = SubscriptionUtils.generateSubscriptionRequest(
        userResponseDto.getId());
    SubscriptionResponseDto response = subscriptionApi.create(request);
    assertThat(response).isNotNull();

    assertEquals(response.getUserId(), request.getUserId());
  }

  @Test
  public void saveSubscriptionTestWithNotExistingUser_shouldThrowException() {
    assertThrows(EntityNotFoundException.class, () -> subscriptionApi.create(
        SubscriptionUtils.generateSubscriptionRequest(UUID.randomUUID())));
  }
}
