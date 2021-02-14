package com.ghandreisv.meter.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "METER_READINGS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class MeterReading {
    public static final String ENTITY_TYPE = "Meter Reading";

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "METER_ID")
    private Meter meter;
    private LocalDate date;
    private Long value;
}
