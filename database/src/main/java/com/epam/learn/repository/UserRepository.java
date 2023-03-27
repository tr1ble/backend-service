package com.epam.learn.repository;

import com.epam.learn.model.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
  User findByUsername(String username);
}
