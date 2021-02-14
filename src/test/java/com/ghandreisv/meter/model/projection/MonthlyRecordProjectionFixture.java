package com.ghandreisv.meter.model.projection;

import java.time.LocalDate;

public class MonthlyRecordProjectionFixture {

    public static MonthlyRecordProjection withDateAndTotal(LocalDate date, Long total) {
        return new MonthlyRecordProjection() {
            @Override
            public LocalDate getDate() {
                return date;
            }

            @Override
            public Long getTotal() {
                return total;
            }
        };
    }

}
