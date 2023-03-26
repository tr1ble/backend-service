package com.epam.learn.mapper;

import com.epam.learn.config.MappersConfig;
import com.epam.learn.model.Role;
import com.epam.learn.model.User;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappersConfig.class)
public abstract class UserMapper {

  protected static Set<Role> mapSetValues(Set<UUID> set, Function<UUID, Role> function) {
    return
        set != null ? set.stream()
            .map(function)
            .collect(Collectors.toSet())
            : new HashSet<>();
  }

  protected static List<String> mapSetToList(Set<Role> set, Function<Role, String> function) {
    return set.stream()
        .map(function)
        .collect(Collectors.toList());
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "roles", expression = "java(UserMapper.mapSetValues(source.getRoles(), role -> new Role(role)))")
  public abstract User mapToDomain(UserRequestDto source);

  @Mapping(target = "roles", expression = "java(UserMapper.mapSetToList(user.getRoles(), role -> role.getId().toString()))")
  public abstract com.epam.learn.User mapToAvro(User user);

  public abstract UserResponseDto mapToResponse(User source);
}
