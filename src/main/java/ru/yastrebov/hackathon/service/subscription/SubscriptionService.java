package ru.yastrebov.hackathon.service.subscription;

import ru.yastrebov.hackathon.model.Subscription;

public interface SubscriptionService {

    Subscription getSubscription(String lastName, String firstName);

    Subscription createSubscription(Subscription newSubscription);

    void deleteSubscription(Long id);

}
