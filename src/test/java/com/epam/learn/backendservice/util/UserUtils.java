package com.epam.learn.backendservice.util;

import com.epam.learn.backendservice.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserUtils {
  private static final String USERNAME = "test";
  private static final String PASSWORD = "test";

  public static List<User> generateNewUsers(int number) {
    List<User> users = new ArrayList<>(number);
    while(number > 0) {
      users.add(User
          .builder()
              .username(USERNAME + number)
              .password(PASSWORD + number)
          .build());
      number--;
    }
    return users;
  }

  public static User generateExistingUser(int number) {
    return User
        .builder()
        .id(UUID.randomUUID())
        .username(USERNAME)
        .password(PASSWORD)
        .build();
  }
}
