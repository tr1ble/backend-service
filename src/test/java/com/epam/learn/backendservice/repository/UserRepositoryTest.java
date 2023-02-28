package com.epam.learn.backendservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.epam.learn.backendservice.model.User;
import com.epam.learn.backendservice.util.UserUtils;
import java.util.List;
import java.util.Optional;
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
  private UserRepository userRepository;

  @Test
  public void whenCalledSave_thenCorrectNumberOfUsers() {
    userRepository.saveAll(UserUtils.generateNewUsers(USER_COUNT));
    List<User> users = (List<User>) userRepository.findAll();

    assertThat(users.size()).isEqualTo(USER_COUNT);
  }

  @Test
  public void whenCalledSave_thenUpdateUser() {
    long id = userRepository.save(UserUtils.generateExistingUser(4)).getId();
    Optional<User> user = userRepository.findById(id);

    assertTrue(user.isPresent());
    assertEquals(user.get().getUsername(), "test");
  }
}
