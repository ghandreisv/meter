package com.ghandreisv.meter.service.meterreading;

import com.ghandreisv.meter.service.meter.Meter;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "METER_READINGS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class MeterReading {
    public static final String ENTITY_TYPE = "Meter Reading";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @ManyToOne
    @JoinColumn(name = "METER_ID")
    private Meter meter;
    private LocalDate date;
    private Long value;

    public MeterReading(Meter meter, LocalDate date, Long value) {
        this.meter = meter;
        this.date = date;
        this.value = value;
    }
}
