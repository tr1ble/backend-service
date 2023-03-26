package com.epam.learn.dto.role;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RoleResponseDto extends RepresentationModel<RoleResponseDto> {
  private UUID id;
  private String name;
}
