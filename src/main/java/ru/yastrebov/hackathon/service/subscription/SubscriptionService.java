package ru.yastrebov.hackathon.service.subscription;

import ru.yastrebov.hackathon.model.Subscription;

public interface SubscriptionService {

    Subscription createSubscription(Subscription consumer);

    void deleteSubscription(Long id);

}
