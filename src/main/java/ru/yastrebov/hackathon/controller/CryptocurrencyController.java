//package ru.yastrebov.hackathon.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.yastrebov.hackathon.model.Subscription;
//import ru.yastrebov.hackathon.model.Cryptocurrency;
//import ru.yastrebov.hackathon.service.currency.CryptocurrencyService;
//
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/cryptocurrency")
//public class CryptocurrencyController {
//
//    private final CryptocurrencyService employeeService;
//
//        @Operation(
//            summary = "Получение данных из API",
//            description = "Направляет запрос в API на обновленные данные")
//    @Scheduled(fixedDelay = 3000)
//    @GetMapping("https://api.com") //TODO вставить правильный URL
//
//    }
//
//    @Operation(
//            summary = "Запись данных в таблицу",
//            description = "Записывает полученные от API данные в БД")
//    @PostMapping
//    public Cryptocurrency postData(@RequestBody Cryptocurrency cryptocurrency) {
//        Cryptocurrency savedCurrency = cryptocurrency;
//
//        return savedCurrency;
//    }
//    @Operation(
//            summary = "Вычисление изменение курса валюты",
//            description = "Вычисляет процентное изменение курса валюты")
//    public Double rateComparsion(Cryptocurrency cryptocurrency, Cryptocurrency savedCurrency) {
//
//        Double lastSavedRate = cryptocurrency.getCurrentRate();
//        Double receivedRate = savedCurrency.getCurrentRate();
//
//        Double rateChange = receivedRate/lastSavedRate;
//
//        return rateChange;
//    }
//
//    public ResponseEntity<String> sendMessage(Subscription consumer, Double rateChange) {
//
////        if(rateChange > consumer.getMaxRateChange())
////        return new ResponseEntity<String>(("Изменение курса криптовалюты " + consumer.getCurrency() + " превысило установленный порог!"), HttpStatus.OK);
////    } else if(rateChange < consumer.getMinRateChange())
//        return null;
//    }
//}
