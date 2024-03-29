package com.epam.learn.service;

import java.util.List;

public interface SecurityService {
  String findLoggedInUsername();
  void loginFailed(final String key);
  boolean isBlocked(String username);
  List<String> getBlockedUsers();
}
