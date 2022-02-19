package ru.yastrebov.hackathon.service.criptocurrency.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yastrebov.hackathon.model.Subscription;
import ru.yastrebov.hackathon.model.Cryptocurrency;
import ru.yastrebov.hackathon.model.enums.Currency;
import ru.yastrebov.hackathon.repository.CryptocurrencyRepository;
import ru.yastrebov.hackathon.service.CryptoCurrencyStubService;
import ru.yastrebov.hackathon.service.criptocurrency.CryptocurrencyService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    private final RestTemplate restTemplate;
    private final CryptocurrencyRepository cryptocurrencyRepository;

    @Override
//    @Scheduled(fixedDelay = 3000)
    public void getRate(long id) {

        Cryptocurrency cryptocurrency = cryptocurrencyRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        double latestCryptocurrencyRate = cryptocurrency.getCurrentRate();

        CryptoCurrencyStubService currentRandomRate = new CryptoCurrencyStubService();

        currentRandomRate.getRandomRate(latestCryptocurrencyRate);

        System.out.println(currentRandomRate);

    }

    @Override
    public Double rateComparison(Cryptocurrency cryptocurrency, Cryptocurrency latestCryptocurrency) {

        Double rateChange = cryptocurrency.getCurrentRate() / latestCryptocurrency.getCurrentRate();

        return rateChange;
    }

    @Override
    public ResponseEntity<String> sendMessage(Subscription subscription, Double rateChange) {

        if (rateChange > subscription.getMaxRateChange()) {
            return new ResponseEntity<String>(("Изменение курса криптовалюты " + subscription.getCurrencyShortName() + " превысило установленный порог. Продавай!"), HttpStatus.OK);
        } else if (rateChange < subscription.getMinRateChange()) {
            return new ResponseEntity<String>(("Изменение курса криптовалюты " + subscription.getCurrencyShortName() + " превысило установленный порог. Покупай!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(("Изменение курса в заданном диапазоне"), HttpStatus.OK);
        }
    }

    @Override
    public Cryptocurrency postData(Cryptocurrency cryptocurrency) {
        Cryptocurrency savedCurrency = cryptocurrencyRepository.save(cryptocurrency);

        return savedCurrency;
    }

}
