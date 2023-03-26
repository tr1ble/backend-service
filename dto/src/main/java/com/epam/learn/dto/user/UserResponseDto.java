package com.epam.learn.dto.user;

import com.epam.learn.dto.role.RoleRequestDto;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponseDto extends RepresentationModel<UserResponseDto> {
  private UUID id;
  private String username;
  private String password;
  private Set<RoleRequestDto> roles;
}
