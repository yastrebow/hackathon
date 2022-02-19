package ru.yastrebov.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yastrebov.hackathon.model.Cryptocurrency;
import ru.yastrebov.hackathon.model.enums.Currency;

import java.time.LocalDateTime;


@Repository
public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Long> {

    Cryptocurrency findFirstByCurrencyShortNameOrderBySnapshotDesc(Currency currencyName);


}
