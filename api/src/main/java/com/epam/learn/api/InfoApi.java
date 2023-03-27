package com.epam.learn.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Info API", description = "Information about application API interface")
public interface InfoApi {
  @Operation(summary = "About", description = "Get general information about application")
  @GetMapping("/about")
  @ResponseStatus(HttpStatus.OK)
  String about();
}
