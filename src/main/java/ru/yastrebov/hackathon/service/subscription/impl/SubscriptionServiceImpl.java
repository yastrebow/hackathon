package ru.yastrebov.hackathon.service.subscription.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yastrebov.hackathon.model.Subscription;
import ru.yastrebov.hackathon.repository.SubscriptionRepository;
import ru.yastrebov.hackathon.service.subscription.SubscriptionService;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Subscription consumer) {


        final Subscription createSubscription = subscriptionRepository.save(consumer);
        return createSubscription;
    }

    @Override
    public void deleteSubscription(Long id) {
        final Subscription subscriptionForDelete = subscriptionRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        subscriptionRepository.delete(subscriptionForDelete);

    }
}
