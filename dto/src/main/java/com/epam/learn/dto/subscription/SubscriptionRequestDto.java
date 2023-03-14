package com.epam.learn.dto.subscription;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionRequestDto {
  private UUID id;
  private UUID userId;
}
