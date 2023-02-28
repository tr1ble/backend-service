package com.epam.learn.backendservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.epam.learn.backendservice.model.User;
import java.util.List;
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

  @Autowired
  private UserRepository userRepository;

  @Test
  public void whenCalledSave_thenCorrectNumberOfUsers() {
    userRepository.save(new User(1L,"test", "test"));
    userRepository.save(new User(2L,"test1", "test1"));
    List<User> users = (List<User>) userRepository.findAll();

    assertThat(users.size()).isEqualTo(2);
  }
}
