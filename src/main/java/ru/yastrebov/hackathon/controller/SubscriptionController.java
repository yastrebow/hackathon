package ru.yastrebov.hackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yastrebov.hackathon.model.Subscription;
import ru.yastrebov.hackathon.service.subscription.SubscriptionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Operation(
            summary = "Запрос данных о подписке с фронта",
            description = "Направляет запрос на фронт на данные по подписке")
    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {

        Subscription newSubscription = subscriptionService.createSubscription(subscription);

        return ResponseEntity.ok(newSubscription);
    }

    @PutMapping
    public ResponseEntity<Subscription> updateSubscription(@RequestBody Subscription subscription) {

        Subscription updatedSubscription = subscriptionService.updateSubscription(subscription);

        return ResponseEntity.ok(updatedSubscription);
    }

}
