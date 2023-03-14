package com.epam.learn.dto.subscription;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class SubscriptionResponseDto extends RepresentationModel<SubscriptionResponseDto> {
  UUID id;
  UUID userId;
  LocalDate startDate;
}
