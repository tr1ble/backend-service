package com.epam.learn.repository;

import com.epam.learn.model.Role;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ListCrudRepository<Role, UUID> {
  Role findByName(String name);
}
