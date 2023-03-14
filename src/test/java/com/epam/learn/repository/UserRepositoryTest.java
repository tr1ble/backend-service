package com.epam.learn.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.epam.learn.dto.user.UserDto;
import com.epam.learn.util.UserUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("DEV")
public class UserRepositoryTest {
  private static final int USER_COUNT = 5;

  @Autowired
  private transient UserRepository userRepository;

  @Test
  public void whenCalledSaveAll_thenCorrectNumberOfUsers() {
    userRepository.saveAll(UserUtils.generateNewUsers(USER_COUNT));
    List<UserDto> users = (List<UserDto>) userRepository.findAll();

    assertThat(users.size()).isEqualTo(USER_COUNT);
  }

  @Test
  public void whenCalledSave_thenCreateUserAndGetById() {
    UUID id = userRepository.save(UserUtils.generateNewUser()).getId();
    Optional<UserDto> user = userRepository.findById(id);

    assertTrue(user.isPresent());
    assertEquals(user.get().getUsername(), "test");
  }

  @Test
  public void whenCalledDelete_thenDeleteUserAndNotPresent() {
    userRepository.save(UserUtils.generateNewUser());
    UUID id = userRepository.findAll().iterator().next().getId();
    userRepository.deleteById(id);
    Optional<UserDto> user = userRepository.findById(id);

    assertFalse(user.isPresent());
  }
}
