package ru.yastrebov.hackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yastrebov.hackathon.service.criptocurrency.CryptocurrencyService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cryptocurrency")
public class CryptocurrencyController {

    private final CryptocurrencyService cryptocurrencyService;

    @Operation(
            summary = "Запрос последней записи для фронта",
            description = "Направляет запрос в БД на последние данные")
    @GetMapping("/{lastName}/{firstName}")
    public ResponseEntity<String> getLastCryptocurrency(@PathVariable String lastName,
                                                        @PathVariable String firstName) {

        return ResponseEntity.ok(cryptocurrencyService.getLastCryptocurrency(lastName, firstName));
    }
}
