package com.epam.learn.api;

import com.epam.learn.dto.authentication.AuthenticationRequest;
import com.epam.learn.dto.authentication.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Auth API", description = "Authentication API for users")
public interface AuthApi {
  @Operation(summary = "Login", description = "Login by user")
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authenticationRequest);
  @Operation(summary = "Registration", description = "Registration by user")
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  AuthenticationResponse registration(@Valid @RequestBody AuthenticationRequest authenticationRequest);

  @Operation(summary = "Get blocked", description = "Get blocked users IPs")
  @PostMapping("/getBlocked")
  @ResponseStatus(HttpStatus.OK)
  List<String> getBlocked();
}
