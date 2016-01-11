package com.example.elliottross23.androidcomponents.calendar.internals;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.elliottross23.androidcomponents.calendar.CalendarSimpleDate;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elliottross on 1/6/16.
 */
public abstract class CalendarAdapter extends BaseAdapter {
    protected Activity activity;
    private List<CalendarSimpleDate> gridList;
    private int currentDay;
    private int currentMonth;
    private int currentYear;
    private int numberOfCellsToLoadAtOneTime;
    private LocalDate localDate;
    private int numberOfColumns;

    public CalendarAdapter(Activity activity, int numRowsVisible, int numberOfColumns) {
        this.activity = activity;
        this.numberOfColumns = numberOfColumns;
        numberOfCellsToLoadAtOneTime = (numRowsVisible * 2) * this.numberOfColumns;
        if(numberOfCellsToLoadAtOneTime % 2 == 0) { numberOfCellsToLoadAtOneTime++; }
        this.activity = activity;
        gridList = new ArrayList<>();
        init();
    }

    /**
     * Override this method to create your own list items. It's abstract so you have to
     * override!
     * @param position
     * @param convertView
     * @param parent
     * @param date
     * @return
     */
    public abstract View getView(int position, View convertView, ViewGroup parent, CalendarSimpleDate date);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent, gridList.get(position));
    }

    private void init() {
        CalendarSimpleDate date;
        if(getCount() == 0) {
            // set today's variables
            localDate = LocalDate.now();
            currentDay = localDate.getDayOfMonth();
            currentMonth = localDate.getMonthOfYear();
            currentYear = localDate.getYear();
            String dayName = localDate.dayOfWeek().getAsShortText();
            // create and add the first day
            date = new CalendarSimpleDate(currentDay, currentMonth, currentYear, dayName);
            gridList.add(date);
        }

        loadMore();
    }

    public void loadMore() {
        CalendarSimpleDate date;
        CalendarSimpleDate lastDate = gridList.get(0);
        localDate = localDate.withYear(lastDate.getYear()).withMonthOfYear(lastDate.getMonthOfYear()).withDayOfMonth(lastDate.getDayOfMonth());
        for(int i = 0; i < numberOfCellsToLoadAtOneTime; i++) {
            localDate = localDate.minusDays(1);
            String dayName = localDate.dayOfWeek().getAsShortText();
            date = new CalendarSimpleDate(localDate.getDayOfMonth(), localDate.getMonthOfYear(), localDate.getYear(), dayName);
            gridList.add(0, date);
        }

        notifyDataSetChanged();
    }

    public boolean isToday(CalendarSimpleDate date) {
        return (currentDay == date.getDayOfMonth()) &&
               (currentMonth == date.getMonthOfYear()) &&
               (currentYear == date.getYear());
    }

    @Override
    public int getCount() {
        return gridList.size();
    }

    @Override
    public CalendarSimpleDate getItem(int position) {
        return gridList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getNumberOfCellsToLoadAtOneTime() {
        return numberOfCellsToLoadAtOneTime;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }
}
