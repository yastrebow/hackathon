package ru.yastrebov.hackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yastrebov.hackathon.model.Subscription;
import ru.yastrebov.hackathon.repository.SubscriptionRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

//    private final SubscriptionRepository subscriptionRepository;
//
//    @Operation(
//            summary = "Запрос данных о подписке с фронта",
//            description = "Направляет запрос на фронт на данные по подписке")
//    @GetMapping
//    public Subscription getNewSubscribe() {
//
//        Subscription newSubscription = subscriptionRepository.findFirstByOrderByCreatedAtDesc();
//
//        return newSubscription;
//    }



}
