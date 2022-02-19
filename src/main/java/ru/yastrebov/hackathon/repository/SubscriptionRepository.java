package ru.yastrebov.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yastrebov.hackathon.model.Subscription;

import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
