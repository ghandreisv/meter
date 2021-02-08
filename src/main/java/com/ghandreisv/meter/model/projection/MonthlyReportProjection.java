package com.ghandreisv.meter.model.projection;

import java.time.LocalDate;

public interface MonthlyReportProjection {
     LocalDate getDate();
     Long getTotal();
}
