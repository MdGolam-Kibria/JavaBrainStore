package com.CrackCode.interviewQuestion.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static void main(String[] args) throws ParseException {
        System.out.println("12 hour dateTime = " + DateUtil.get12hourCurrentDateTime());
        System.out.println("24 hour dateTime = " + DateUtil.get24hourCurrentDateTime());
        System.out.println("");
        System.out.println("CustomDateTime = " + DateUtil.getCustomCurrentDateTime("yyy/MM/dd hh:mm:ss a"));
        System.out.println("CustomDateTime 24 hourDate Time = " + DateUtil.getCustomCurrentDateTime("yyy/MM/dd HH:mm:ss a"));
        System.out.println("Day Between two date way 1 = "+DateUtil.dayBetweenTwoDate("12/5/2021 5:2:12 PM","22/5/2021 5:2:12 PM","dd/MM/yyy hh:mm:ss a"));
        System.out.println("Day Between two date way 2 = "+DateUtil.dayBetweenTwoDate("12/5/2021","22/5/2021","dd/MM/yyy"));
        System.out.println("Day Between two date way 3 = "+DateUtil.dayBetweenTwoDate("12/5/2021 5:2:12 PM","22/5/2021 5:2:12 PM","dd/MM/yyy"));

    }

    public static String get12hourCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String get24hourCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String getCustomCurrentDateTime(String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String dayBetweenTwoDate(String startDate, String endDate, String datePattern) throws ParseException {
        /**
         * here datePattern like below
         * yyyy-MM-dd hh:mm:ss a //for 12 hour date time
         * yyyy-MM-dd HH:mm:ss a //for 24 hour date time
         * MM/dd/yyyy //only date
         */
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.ENGLISH);
        Date firstDate = sdf.parse(startDate);
        Date secondDate = sdf.parse(endDate);
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long dayDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return String.valueOf(dayDiff);
    }


}
