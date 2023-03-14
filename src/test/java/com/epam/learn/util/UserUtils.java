package com.epam.learn.util;

import com.epam.learn.dto.user.UserDto;
import com.epam.learn.dto.user.UserRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserUtils {
  private static final String USERNAME = "test";
  private static final String PASSWORD = "test";

  public static List<UserDto> generateNewUsers(int number) {
    List<UserDto> users = new ArrayList<>(number);
    while(number > 0) {
      users.add(UserDto
          .builder()
              .username(USERNAME + number)
              .password(PASSWORD + number)
          .build());
      number--;
    }
    return users;
  }

  public static UserDto generateNewUser() {
    return UserDto
        .builder()
        .id(UUID.randomUUID())
        .username(USERNAME)
        .password(PASSWORD)
        .build();
  }

  public static UserRequestDto generateUserRequest() {
    return UserRequestDto
        .builder()
        .id(UUID.randomUUID())
        .username(USERNAME)
        .password(PASSWORD)
        .build();
  }
}
