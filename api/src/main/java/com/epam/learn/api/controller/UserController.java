package com.epam.learn.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.epam.learn.api.UserApi;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import com.epam.learn.service.UserService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

  private final UserService userService;

  @Override
  public UserResponseDto create(UserRequestDto userRequest) {
    UserResponseDto userResponseDto = userService.create(userRequest);
    userResponseDto.add(
        linkTo(methodOn(UserController.class).create(userRequest))
            .slash(userResponseDto.getId())
            .withSelfRel()
    );

    return userResponseDto;
  }

  @Override
  public UserResponseDto get(UUID id) {
    UserResponseDto userResponseDto = userService.getById(id);
    userResponseDto.add(
        linkTo(methodOn(UserController.class).get(id))
            .withSelfRel()
    );

    return userResponseDto;
  }

  @Override
  public void delete(UUID id) {
    userService.deleteById(id);
  }

  @Override
  public UserResponseDto update(UserRequestDto userRequest) {
    UserResponseDto userResponseDto = userService.update(userRequest);
    userResponseDto.add(
        linkTo(methodOn(UserController.class).create(userRequest))
            .slash(userResponseDto.getId())
            .withSelfRel()
    );

    return userResponseDto;
  }

  @Override
  public List<UserResponseDto> findAll(List<UUID> ids) {
    List<UserResponseDto> userResponseDtos =
        ids == null || ids.isEmpty() ? userService.getAll() : userService.getById(ids);
    for(UserResponseDto userResponseDto : userResponseDtos) {
      userResponseDto.add(
          linkTo(methodOn(UserController.class).get(userResponseDto.getId()))
              .withSelfRel()
      );
    }
    return userResponseDtos;
  }

  @Override
  public void send(UUID id) {
    userService.send(id);
  }
}
