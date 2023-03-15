package com.epam.learn.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.learn.dto.user.UserResponseDto;
import com.epam.learn.service.impl.UserServiceImpl;
import com.epam.learn.util.UserUtils;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("DEV")
public class UserServiceTest {
  @Autowired
  private transient UserServiceImpl userService;

  @Test
  public void whenCalledSave_thenCreateUserAndGetById() {
    UUID id = userService.create(UserUtils.generateUserRequest()).getId();
    UserResponseDto userResponseDto = userService.getById(id);

    assertEquals(userResponseDto.getUsername(), "test");
  }

  @Test
  public void whenCalledDelete_thenDeleteUserAndThrowException() {
    UUID id = userService.getAll().iterator().next().getId();
    userService.deleteById(id);

    assertThrows(EntityNotFoundException.class, () -> userService.getById(id));
  }
}
