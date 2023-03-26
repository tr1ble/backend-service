package com.epam.learn.security;

import com.epam.learn.service.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  private HttpServletRequest request;
  private SecurityService service;

  @Override
  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
    final String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(request.getRemoteAddr())) {
      service.loginFailed(request.getRemoteAddr());
    } else {
      service.loginFailed(xfHeader.split(",")[0]);
    }
  }
}
