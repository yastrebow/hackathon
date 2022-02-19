package ru.yastrebov.hackathon.service.criptocurrency;

import org.springframework.http.ResponseEntity;
import ru.yastrebov.hackathon.model.Subscription;
import ru.yastrebov.hackathon.model.Cryptocurrency;
import ru.yastrebov.hackathon.model.enums.Currency;


public interface CryptocurrencyService {

    void getRate();

    Cryptocurrency createCryptocurrency(Cryptocurrency cryptocurrency);

    Double rateComparison(Cryptocurrency oldCryptocurrency, Double newRate);

    ResponseEntity<String> sendMessage(Subscription consumer, Double rateChange);
}
