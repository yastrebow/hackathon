package ru.yastrebov.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import ru.yastrebov.hackathon.model.enums.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cryptocurrency")
public class Cryptocurrency { //TODO привести в соответствие с заданием

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "currency_short_name", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private Currency currencyShortName;

    @Column(name="current_rate", nullable = false)
    private Double currentRate;

    @Column(name = "snapshot", nullable = false)
    @CreationTimestamp
    private LocalDateTime snapshot;

    @Column(name="rate_change", nullable = false)
    private Double rateChange;
}
