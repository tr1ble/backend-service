package com.epam.learn.security;

import com.epam.learn.service.SecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
  private SecurityService securityService;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws ServletException, IOException {
    super.onAuthenticationFailure(request, response, exception);
    String errorMessage;
    errorMessage = "Bad credentials";
    if (securityService.isBlocked(request.getUserPrincipal().getName())) {
      errorMessage = "Max login attempts exceed";
    }
    if (exception.getMessage().equalsIgnoreCase("blocked")) {
      errorMessage = "User is blocked";
    }
    request.getSession()
        .setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
  }
}
