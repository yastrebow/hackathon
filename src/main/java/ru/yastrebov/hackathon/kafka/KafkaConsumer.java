package ru.yastrebov.hackathon.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.yastrebov.hackathon.service.currency.impl.CryptocurrencyServiceImpl;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

//    private final CryptocurrencyServiceImpl cryptocurrencyService;
//
//    @KafkaListener(topics = "rht.candles", groupId = "${spring.kafka.consumer.group-id}")
//    private void listenGroupEmployee(ConsumerRecord<String, String> message) {
//
//        log.info("Receive Message: " + message);
//
//        //do something??
//    }

}