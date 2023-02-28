package com.epam.learn.backendservice.repository;

import com.epam.learn.backendservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
