package com.epam.learn.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.epam.learn.api.SubscriptionApi;
import com.epam.learn.dto.subscription.SubscriptionRequestDto;
import com.epam.learn.dto.subscription.SubscriptionResponseDto;
import com.epam.learn.service.SubscriptionService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SubscriptionController implements SubscriptionApi {

  private final SubscriptionService subscriptionService;

  @Override
  public SubscriptionResponseDto create(SubscriptionRequestDto subscriptionRequest) {
    SubscriptionResponseDto subscriptionResponseDto = subscriptionService.create(
        subscriptionRequest);
    subscriptionResponseDto.add(
        linkTo(methodOn(SubscriptionController.class).create(subscriptionRequest))
            .slash(subscriptionResponseDto.getId())
            .withSelfRel());

    return subscriptionResponseDto;
  }

  @Override
  public SubscriptionResponseDto get(UUID id) {
    SubscriptionResponseDto subscriptionResponseDto = subscriptionService.getById(id);
    subscriptionResponseDto.add(
        linkTo(methodOn(SubscriptionController.class).get(id)).withSelfRel()
    );

    return subscriptionResponseDto;
  }

  @Override
  public void delete(UUID id) {
    subscriptionService.deleteById(id);
  }

  @Override
  public SubscriptionResponseDto update(SubscriptionRequestDto subscriptionRequest) {
    SubscriptionResponseDto subscriptionResponseDto = subscriptionService.update(subscriptionRequest);
    subscriptionResponseDto.add(
        linkTo(methodOn(SubscriptionController.class).update(subscriptionRequest))
            .slash(subscriptionResponseDto.getId())
            .withSelfRel()
    );

    return subscriptionResponseDto;
  }

  @Override
  public List<SubscriptionResponseDto> findAll() {
    List<SubscriptionResponseDto> subscriptionResponseDtos = subscriptionService.getAll();
    for(SubscriptionResponseDto subscriptionResponseDto : subscriptionResponseDtos) {
      subscriptionResponseDto.add(
          linkTo(methodOn(SubscriptionController.class).get(subscriptionResponseDto.getId()))
              .withSelfRel()
      );
    }

    return subscriptionResponseDtos;
  }
}
