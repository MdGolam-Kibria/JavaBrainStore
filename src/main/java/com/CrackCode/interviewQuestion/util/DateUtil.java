package com.CrackCode.interviewQuestion.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static void main(String[] args) throws ParseException {
        System.out.println("12 hour dateTime = " + DateUtil.get12hourCurrentDateTime());
        System.out.println("24 hour dateTime = " + DateUtil.get24hourCurrentDateTime());
        System.out.println("");
        System.out.println("CustomDateTime = " + DateUtil.getCustomCurrentDateTime("yyy/MM/dd hh:mm:ss a"));
        System.out.println("CustomDateTime 24 hourDate Time = " + DateUtil.getCustomCurrentDateTime("yyy/MM/dd HH:mm:ss a"));
        System.out.println("Day Between two date way 1 = " + DateUtil.dayBetweenTwoDate("12/5/2021 5:2:12 PM", "22/5/2021 5:2:12 PM", "dd/MM/yyy hh:mm:ss a"));
        System.out.println("Hour Between two date = " + DateUtil.hourBetweenTwoDate("12/5/2021 5:0:0 PM", "12/5/2021 10:10:0 PM", "dd/MM/yyy hh:mm:ss a"));
        System.out.println("Day Between two date way 2 = " + DateUtil.dayBetweenTwoDate("12/5/2021", "22/5/2021", "dd/MM/yyy"));
        System.out.println("Day Between two date way 3 = " + DateUtil.dayBetweenTwoDate("12/5/2021 5:2:12 PM", "22/5/2021 5:2:12 PM", "dd/MM/yyy"));
        System.out.println("Your Expiration Time = " + DateUtil.getExpirationTime(Long.parseLong("5")));
        System.out.println("Get Year From Date = " + DateUtil.getYearFromDate(new SimpleDateFormat("yyy/MM/dd HH:mm:ss a", Locale.ENGLISH).parse("2022/2/2 12:5:20 PM")));
        System.out.println("Is Same Day = " + DateUtil.isSameDay("2021/5/15 5:2:12 PM", "2021/5/12 5:2:12 PM"));
        System.out.println();
        System.out.println();
        System.out.println();
        /**
         * all info between to date :) :)
         */
        Map<String, Long> datesInfo = DateUtil.getDateDifferenceInfo("12/5/2021 5:0:0 PM", "12/5/2021 10:10:0 PM", "dd/MM/yyy hh:mm:ss a");
        System.out.println(""+datesInfo);
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

    public static Long hourBetweenTwoDate(String startDate, String endDate, String datePattern) throws ParseException {
        /**
         * here count only hour not minute
         */
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.ENGLISH);
        Date firstDate = sdf.parse(startDate);
        Date secondDate = sdf.parse(endDate);
        long hour = Math.abs(firstDate.getTime() - secondDate.getTime()) / 3600000;
        return hour;
    }

    public static Date getExpirationTime(Long expireHours) {
        Date now = new Date();
        Long expireInMilis = TimeUnit.HOURS.toMillis(expireHours);
        return new Date(expireInMilis + now.getTime());
    }

    public static Integer getYearFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static boolean isSameDay(String fastDate, String sndDate) {
        String databasedate = fastDate.substring(0, 10);
        String currentDayDate = sndDate.substring(0, 10);
        if (databasedate.equals(currentDayDate)) {
            return true;
        }
        return false;
    }

    public static Map<String, Long> getDateDifferenceInfo(String startdate, String enddate, String datePattern) throws ParseException {
        /**
         * below comment code is sample input format you can use any valid date format here
         */
       /* SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd HH:mm:ss a", Locale.ENGLISH);
        Date startDate = sdf.parse("2021/5/10 5:2:12 PM"); //set your start time
        Date endDate = sdf.parse("2021/5/12 5:2:12 PM"); // set  your end time*/

        SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.ENGLISH);
        Date startDate = sdf.parse(startdate); //set your start time
        Date endDate = sdf.parse(enddate); // set  your end time


        long duration = endDate.getTime() - startDate.getTime();
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);

        Map<String, Long> map = new HashMap<>();
        map.put("Duration", duration);
        map.put("diffInDays", diffInDays);
        map.put("diffInHours", diffInHours);
        map.put("diffInMinutes", diffInMinutes);
        map.put("diffInSeconds", diffInSeconds);
        return new HashMap<>(map);
    }

}
