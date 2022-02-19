package ru.yastrebov.hackathon.service.criptocurrency.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.yastrebov.hackathon.model.Cryptocurrency;
import ru.yastrebov.hackathon.model.Subscription;
import ru.yastrebov.hackathon.model.enums.Currency;
import ru.yastrebov.hackathon.model.enums.ExceedOrNotThreshold;
import ru.yastrebov.hackathon.repository.CryptocurrencyRepository;
import ru.yastrebov.hackathon.service.CryptocurrencyRandomizer;
import ru.yastrebov.hackathon.service.criptocurrency.CryptocurrencyService;
import ru.yastrebov.hackathon.service.subscription.SubscriptionService;

import java.time.LocalDateTime;

import static ru.yastrebov.hackathon.model.enums.ExceedOrNotThreshold.EXCEED_MAX;
import static ru.yastrebov.hackathon.model.enums.ExceedOrNotThreshold.EXCEED_MIN;

@Service
@RequiredArgsConstructor
@Slf4j
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    private final CryptocurrencyRandomizer cryptocurrencyRandomizer;
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final SubscriptionService subscriptionService;

    @Override
    @Scheduled(fixedDelay = 3000)
    public void getRate() {
        Cryptocurrency previousCryptocurrency = cryptocurrencyRepository.findFirstByCurrencyShortNameOrderBySnapshotDesc(Currency.BTC);
        log.info("previousCryptocurrency {}", previousCryptocurrency);
        Double randomRate = cryptocurrencyRandomizer.getRandomRate(previousCryptocurrency.getCurrentRate());
        cryptocurrencyHandler(randomRate, previousCryptocurrency);
    }

    @Override
    public String getLastCryptocurrency(String lastName, String firstName) {
        Cryptocurrency lastCryptocurrency = cryptocurrencyRepository.findFirstByCurrencyShortNameOrderBySnapshotDesc(Currency.BTC);

        Subscription subscription = subscriptionService.getSubscription(lastName, firstName);

        ExceedOrNotThreshold exceedOrNotThreshold = checkThresholdExceeding(subscription, lastCryptocurrency);
        log.debug("exceedOrNotThreshold = {}", exceedOrNotThreshold.name());
        return sendMessage(subscription, lastCryptocurrency, exceedOrNotThreshold);
    }

    private ExceedOrNotThreshold checkThresholdExceeding(Subscription subscription, Cryptocurrency lastCryptocurrency) {
        Double cryptocurrencyRateChange = lastCryptocurrency.getRateChange();
        Double maxRateChange = subscription.getMaxRateChange();
        Double minRateChange = subscription.getMinRateChange();

        if (cryptocurrencyRateChange >= 0) {
            return cryptocurrencyRateChange > maxRateChange ? EXCEED_MAX : ExceedOrNotThreshold.NOT_EXCEED;
        } else {
            return cryptocurrencyRateChange < minRateChange ? ExceedOrNotThreshold.EXCEED_MIN : ExceedOrNotThreshold.NOT_EXCEED;
        }
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

        return newRate - previousCryptocurrency.getCurrentRate();
    }

    private String sendMessage(Subscription subscription,
                               Cryptocurrency currentCryptocurrency,
                               ExceedOrNotThreshold exceedOrNotThreshold) {
        String cryptocurrencyName = currentCryptocurrency.getCurrencyShortName().name();
        Double currentRate = currentCryptocurrency.getCurrentRate();
        if (exceedOrNotThreshold.equals(EXCEED_MAX)) {
            return "Изменение курса криптовалюты " + cryptocurrencyName + " превысило установленный порог: " +
                    subscription.getMaxRateChange() + ". Продавай! Текущее значение валюты " + cryptocurrencyName +
                    ": " + currentRate;
        } else if (exceedOrNotThreshold.equals(EXCEED_MIN)) {
            return "Изменение курса криптовалюты " + cryptocurrencyName + " превысило установленный порог:" +
                    subscription.getLastName() + ". Покупай! Текущее значение валюты " + cryptocurrencyName + ": " +
                    currentRate;
        } else {
            return "Изменение курса в заданном диапазоне. Текущее значение валюты " + cryptocurrencyName + ": "
                    + currentRate;
        }
    }

}
