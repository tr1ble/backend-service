package com.epam.learn.repository;

import com.epam.learn.model.Subscription;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, UUID> {
}
