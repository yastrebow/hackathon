package ru.yastrebov.hackathon.service.currency;

import org.springframework.http.ResponseEntity;
import ru.yastrebov.hackathon.model.Subscription;
import ru.yastrebov.hackathon.model.Cryptocurrency;


public interface CryptocurrencyService {

    void getRate();

    Cryptocurrency postData(Cryptocurrency cryptocurrency);

    Double rateComparesing(Cryptocurrency cryptocurrency, Cryptocurrency savedCurrency);

    ResponseEntity<String> sendMessage(Subscription consumer, Double rateChange);
}
