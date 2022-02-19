package ru.yastrebov.hackathon.service.criptocurrency;

import ru.yastrebov.hackathon.model.Cryptocurrency;


public interface CryptocurrencyService {

    void getRate();

    Cryptocurrency createCryptocurrency(Cryptocurrency cryptocurrency);

    String getLastCryptocurrency(String lastName, String firstName);

    Double rateComparison(Cryptocurrency oldCryptocurrency, Double newRate);
}
