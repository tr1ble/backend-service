package com.epam.learn.repository;

import com.epam.learn.model.User;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, UUID> {
  User findByUsername(String username);
}
