package ru.yastrebov.hackathon.service.criptocurrency.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.yastrebov.hackathon.model.Subscription;
import ru.yastrebov.hackathon.model.Cryptocurrency;
import ru.yastrebov.hackathon.model.enums.Currency;
import ru.yastrebov.hackathon.repository.CryptocurrencyRepository;
import ru.yastrebov.hackathon.service.CryptocurrencyRandomizer;
import ru.yastrebov.hackathon.service.criptocurrency.CryptocurrencyService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    private final CryptocurrencyRandomizer cryptocurrencyRandomizer;
    private final CryptocurrencyRepository cryptocurrencyRepository;

    @Override
    @Scheduled(fixedDelay = 3000)
    public void getRate() {
        Cryptocurrency previousCryptocurrency = cryptocurrencyRepository.findFirstByCurrencyShortNameOrderBySnapshotDesc(Currency.BTC);

        log.info("previousCryptocurrency {}", previousCryptocurrency);

        Double randomRate = cryptocurrencyRandomizer.getRandomRate(previousCryptocurrency.getCurrentRate());

        cryptocurrencyHandler(randomRate, previousCryptocurrency);
    }

    public void cryptocurrencyHandler(Double randomRate, Cryptocurrency previousCryptocurrency) {


        Cryptocurrency cryptocurrency = Cryptocurrency.builder()
                .currencyShortName(Currency.BTC)
                .currentRate(randomRate)
                .snapshot(LocalDateTime.now())
                .rateChange(rateComparison(previousCryptocurrency, randomRate))
                .build();

        createCryptocurrency(cryptocurrency);
    }

    @Override
    public Cryptocurrency createCryptocurrency(Cryptocurrency cryptocurrency) {

        Cryptocurrency savedCurrency = cryptocurrencyRepository.save(cryptocurrency);

        log.info("Currency = {} saved!", savedCurrency);

        return savedCurrency;
    }

    @Override
    public Double rateComparison(Cryptocurrency previousCryptocurrency, Double newRate) {

        Double rateChange = newRate - previousCryptocurrency.getCurrentRate();

        return rateChange;
    }

    @Override
    public ResponseEntity<String> sendMessage(Subscription subscription, Double rateChange) {

        if (rateChange > subscription.getMaxRateChange()) {
            return new ResponseEntity<>(("Изменение курса криптовалюты " + subscription.getCurrencyShortName() + " превысило установленный порог. Продавай!"), HttpStatus.OK);
        } else if (rateChange < subscription.getMinRateChange()) {
            return new ResponseEntity<>(("Изменение курса криптовалюты " + subscription.getCurrencyShortName() + " превысило установленный порог. Покупай!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(("Изменение курса в заданном диапазоне"), HttpStatus.OK);
        }
    }


}
