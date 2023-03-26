package com.epam.learn.dto.role;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleRequestDto {
  private UUID id;
  private String name;
}
