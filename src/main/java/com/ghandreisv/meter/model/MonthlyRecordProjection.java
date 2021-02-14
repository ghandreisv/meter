package com.ghandreisv.meter.model;

import java.time.LocalDate;

public interface MonthlyRecordProjection {
     LocalDate getDate();
     Long getTotal();
}
