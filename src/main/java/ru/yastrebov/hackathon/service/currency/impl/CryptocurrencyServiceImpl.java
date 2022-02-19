package ru.yastrebov.hackathon.service.currency.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yastrebov.hackathon.model.Subscription;
import ru.yastrebov.hackathon.model.Cryptocurrency;
import ru.yastrebov.hackathon.repository.CryptocurrencyRepository;
import ru.yastrebov.hackathon.service.currency.CryptocurrencyService;

@Service
@RequiredArgsConstructor
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    private final RestTemplate restTemplate;
    private final CryptocurrencyRepository cryptocurrencyRepository;

    @Override
//    @Scheduled(fixedDelay = 3000)
    public void getRate() {
        Cryptocurrency cryptocurrency = restTemplate
                .getForObject("https://api.com", Cryptocurrency.class); //TODO вставить правильный URL

        Cryptocurrency latestCryptocurrency = cryptocurrencyRepository.findFirstByCurrencyShortNameOrderBySnapshotDesc(cryptocurrency.getCurrencyShortName());

    }

    @Override
    public Cryptocurrency postData(Cryptocurrency cryptocurrency) {
        Cryptocurrency savedCurrency = cryptocurrencyRepository.save(cryptocurrency);

        return savedCurrency;
    }

    @Override
    public Double rateComparesing(Cryptocurrency cryptocurrency, Cryptocurrency savedCurrency) {
        Double lastSavedRate = cryptocurrency.getCurrentRate();
        Double receivedRate = savedCurrency.getCurrentRate();

        Double rateChange = receivedRate / lastSavedRate;

        return rateChange;
    }

    @Override
    public ResponseEntity<String> sendMessage(Subscription consumer, Double rateChange) {
//
//        if (rateChange > consumer.getMaxRateChange()) {
//            return new ResponseEntity<String>(("Изменение курса криптовалюты " + consumer.getCurrency() + " превысило установленный порог. Продавай!"), HttpStatus.OK);
//        } else if (rateChange < consumer.getMinRateChange()) {
//            return new ResponseEntity<String>(("Изменение курса криптовалюты " + consumer.getCurrency() + " превысило установленный порог. Покупай!"), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<String>(("Изменение курса в заданном диапазоне"), HttpStatus.OK);
//        }
//
    return null;
    }
}
