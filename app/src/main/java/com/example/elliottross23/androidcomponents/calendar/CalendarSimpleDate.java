package com.example.elliottross23.androidcomponents.calendar;

/**
 * Created by elliottross on 1/8/16.
 */
public class CalendarSimpleDate {
    private int dayOfMonth;
    private int monthOfYear;
    private int year;
    private String dayName;

    public CalendarSimpleDate(int day, int month, int year, String dayName) {
        dayOfMonth = day;
        monthOfYear = month;
        this.year = year;
        this.dayName = dayName;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getMonthOfYear() {
        return monthOfYear;
    }

    public int getYear() {
        return year;
    }

    public String getDayName() {
        return dayName;
    }

    @Override
    public String toString() {
        return dayName + " " + String.valueOf(monthOfYear) + "/" +
               String.valueOf(dayOfMonth) + "/" +
               String.valueOf(year);
    }
}
