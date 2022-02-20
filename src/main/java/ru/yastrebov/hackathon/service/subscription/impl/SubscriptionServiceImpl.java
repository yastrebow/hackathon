package ru.yastrebov.hackathon.service.subscription.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yastrebov.hackathon.model.Subscription;
import ru.yastrebov.hackathon.repository.SubscriptionRepository;
import ru.yastrebov.hackathon.service.subscription.SubscriptionService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription getSubscription(String lastName, String firstName) {
        return subscriptionRepository.findByLastNameAndFirstName(lastName, firstName);
    }

    @Override
    public Subscription createSubscription(Subscription newSubscription) {
        log.info("createSubscription starts, subscription = {}", newSubscription);
        final Subscription savedSubscription = subscriptionRepository.save(newSubscription);
        log.info("Subscription = {} saved!", savedSubscription);
        return savedSubscription;
    }

    @Override
    public Subscription updateSubscription(Subscription subscription) {

        Subscription subscriptionForUpdate = subscriptionRepository.findByLastNameAndFirstName(subscription.getLastName(), subscription.getFirstName());

        subscriptionForUpdate.setMaxRateChange(subscription.getMaxRateChange());
        subscriptionForUpdate.setMinRateChange(subscription.getMinRateChange());

        Subscription updatedSubscription = subscriptionRepository.save(subscriptionForUpdate);
        log.info("Updated subscription = {} saved", updatedSubscription);
        return updatedSubscription;
    }

    @Override
    public void deleteSubscription(Long id) {
        final Subscription subscriptionForDelete = subscriptionRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        subscriptionRepository.delete(subscriptionForDelete);

    }
}
