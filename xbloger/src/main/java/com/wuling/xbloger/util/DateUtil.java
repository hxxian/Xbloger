package com.wuling.xbloger.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Author: wu_ling
 * @Date: 2020/5/22
 * @Desc:
 */
public class DateUtil {

    public static LocalDate date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Integer getDayPeriodForNow(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();

        Period period = Period.between(localDate, now);
        return period.getDays();
    }
}
