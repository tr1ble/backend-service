package com.epam.learn.repository;

import com.epam.learn.dto.subscription.SubscriptionDto;
import com.epam.learn.dto.user.UserDto;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionDto, UUID> {
}
