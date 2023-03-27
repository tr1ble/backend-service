package com.epam.learn.service.impl;

import com.epam.learn.service.SecurityService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityServiceImpl implements SecurityService {
  private final HttpServletRequest request;

  public static final int MAX_ATTEMPT = 3;
  private final LoadingCache<String, Integer> attemptsCache;

  public SecurityServiceImpl(HttpServletRequest request) {
    super();
    this.request = request;
    attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build(
        new CacheLoader<>() {
          @Override
          public Integer load(final String key) {
            return 0;
          }
        });
  }

  @Override
  public String findLoggedInUsername() {
    Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
    if (userDetails instanceof UserDetails) {
      return ((UserDetails)userDetails).getUsername();
    }

    return null;
  }

  @Override
  public void loginFailed(String key) {
    int attempts;
    try {
      attempts = attemptsCache.get(key);
    } catch (final ExecutionException e) {
      attempts = 0;
    }
    attempts++;
    attemptsCache.put(key, attempts);
  }

  @Override
  public boolean isBlocked(String username) {
    try {
      return attemptsCache.get(username) >= MAX_ATTEMPT;
    } catch (final ExecutionException e) {
      return false;
    }
  }

  @Override
  public List<String> getBlockedUsers() {
    return attemptsCache.asMap().keySet().stream().filter(key -> {
      try {
        return attemptsCache.get(key) >= MAX_ATTEMPT;
      } catch (ExecutionException e) {
        return false;
      }
    }).toList();
  }
}
