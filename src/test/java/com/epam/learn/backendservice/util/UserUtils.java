package com.epam.learn.backendservice.util;

import com.epam.learn.backendservice.model.UserModel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserUtils {
  private static final String USERNAME = "test";
  private static final String PASSWORD = "test";

  public static List<UserModel> generateNewUsers(int number) {
    List<UserModel> users = new ArrayList<>(number);
    while(number > 0) {
      users.add(UserModel
          .builder()
              .username(USERNAME + number)
              .password(PASSWORD + number)
          .build());
      number--;
    }
    return users;
  }

  public static UserModel generateExistingUser(int number) {
    return UserModel
        .builder()
        .id(UUID.randomUUID())
        .username(USERNAME)
        .password(PASSWORD)
        .build();
  }
}
