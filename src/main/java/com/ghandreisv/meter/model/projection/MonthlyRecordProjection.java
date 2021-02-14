package com.ghandreisv.meter.model.projection;

import java.time.LocalDate;

public interface MonthlyRecordProjection {
     LocalDate getDate();
     Long getTotal();
}
