package com.epam.learn.dto.user;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
  private UUID id;
  private String username;
  private String password;
  @Builder.Default
  private LocalDate birthday = LocalDate.EPOCH;
}
