package ru.yastrebov.hackathon.service;

import org.springframework.stereotype.Service;

@Service
public class CryptocurrencyRandomizer {

    public Double getRandomRate(double lastRate) {

        double max = lastRate * 1.01;
        double min = lastRate * 0.99;

        max -= min;

        return (Math.random() * ++max) + min;
    }

}
