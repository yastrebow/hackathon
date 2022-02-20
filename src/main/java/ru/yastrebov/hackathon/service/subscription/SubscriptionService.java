package ru.yastrebov.hackathon.service.subscription;

import ru.yastrebov.hackathon.model.Subscription;

public interface SubscriptionService {

    Subscription getSubscription(String lastName, String firstName);

    Subscription createSubscription(Subscription newSubscription);

    Subscription updateSubscription(Subscription subscription);

    void deleteSubscription(Long id);

}
