package com.epam.learn.api;

import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import com.epam.learn.dto.subscription.SubscriptionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/subscription")
@Tag(name = "Subscriptions API", description = "Subscriptions API interface")
public interface SubscriptionApi {
  @Operation(summary = "Create subscription", description = "Create new subscription")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  SubscriptionResponseDto create(@Valid @RequestBody SubscriptionRequestDto subscriptionRequest);

  @Operation(summary = "Get subscription by ID", description = "Read subscription by ID")
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  SubscriptionResponseDto get(@PathVariable UUID id);

  @Operation(summary = "Delete subscription", description = "Remove subscription by ID")
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  void delete(@PathVariable UUID id);

  @Operation(summary = "Update subscription", description = "Rewrite subscription")
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  SubscriptionResponseDto update(@Valid @RequestBody SubscriptionRequestDto subscription);

  @Operation(summary = "Get subscriptions", description = "Read all subscriptions")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  List<SubscriptionResponseDto> findAll();
}
