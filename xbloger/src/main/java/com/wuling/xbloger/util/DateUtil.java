package com.wuling.xbloger.util;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public static Date localDate2Date(LocalDate localDate) {
        if(null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取当前年的起止时间
     *
     * @return
     */
    public static String[] getCurrYearPeriod() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        return new String[] {year + "-01-01", year + "-12-31"};
    }


    public static int getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        // 这里记得要-2 原因如下备注
        return calendar.get(Calendar.WEEK_OF_YEAR) - 2;
    }


    public static int getWeekOfYear(LocalDate localDate) {
        Date date = localDate2Date(localDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        // 这里记得要-2 原因如下备注
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020, 1, 8);
        int weekOfYear = getWeekOfYear(localDate);
        System.out.println(weekOfYear);
    }

}
