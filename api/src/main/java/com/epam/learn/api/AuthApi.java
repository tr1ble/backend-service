package com.epam.learn.api;

import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
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
  @Operation(summary = "Registration", description = "Registration by user")
  @PostMapping("/registration")
  @ResponseStatus(HttpStatus.OK)
  UserResponseDto registration(@Valid @RequestBody UserRequestDto userRequest);

  @Operation(summary = "Get blocked", description = "Get blocked users IPs")
  @PostMapping("/getBlocked")
  @ResponseStatus(HttpStatus.OK)
  List<String> getBlocked();
}
