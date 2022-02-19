package ru.yastrebov.hackathon.service;

import org.springframework.stereotype.Service;

@Service
public class CryptoCurrencyStubService {

    public Double getRandomRate(double lastRate) {

        double max = lastRate * 1.1;
        double min = lastRate * 0.9;

        max -= min;

        return (Math.random() * ++max) + min;

    }

}
