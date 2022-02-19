package ru.yastrebov.hackathon.service.criptocurrency;

import ru.yastrebov.hackathon.model.Cryptocurrency;


public interface CryptocurrencyService {

    void getRate();

    Cryptocurrency createCryptocurrency(Cryptocurrency cryptocurrency);

    Double rateComparison(Cryptocurrency oldCryptocurrency, Double newRate);

    public Cryptocurrency getLastCryptocurrency();

//    ResponseEntity<String> sendMessage(Subscription consumer, Double rateChange);
}
