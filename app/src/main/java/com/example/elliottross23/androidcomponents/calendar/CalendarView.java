package com.example.elliottross23.androidcomponents.calendar;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.example.elliottross23.androidcomponents.calendar.internals.CalendarAdapter;

import erlabs.com.everyday.calendar.CalendarSimpleDate;


/**
 * Created by elliottross on 1/8/16.
 */
public class CalendarView extends GridView implements AbsListView.OnScrollListener {
    private int columns;
    private int numberOfCellsLoadedAtOneTime;
    private boolean blockLayoutChildren;

    /* REQUIRED CONSTRUCTOR */
    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /* REQUIRED CONSTRUCTOR */

    @Override
    public void setAdapter(ListAdapter adapter) {
        CalendarAdapter calendarAdapter;
        try {
            calendarAdapter = (CalendarAdapter) adapter;
        } catch(ClassCastException e) {
            Log.e("CalendarView", "Your adapter must extend from CalendarAdapter!");
            throw new CalendarViewException("Your adapter must extend from CalendarAdapter!");
        }

        columns = calendarAdapter.getNumberOfColumns();
        numberOfCellsLoadedAtOneTime = calendarAdapter.getNumberOfCellsToLoadAtOneTime();
        super.setAdapter(calendarAdapter);
        resetOnScrollListener();
    }

    @Override
    protected void layoutChildren() {
        if(!blockLayoutChildren) {
            super.layoutChildren();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) { }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // if we get near the top of the list (two rows before) then load 2x the numberOfRowsToShowAtATime
        if(firstVisibleItem <= columns) {
            setOnScrollListener(null);
            blockLayoutChildren = true;
            ((CalendarAdapter)getAdapter()).loadMore();
            blockLayoutChildren = false;

            int selection = firstVisibleItem + numberOfCellsLoadedAtOneTime + columns + 1;

            setAdapterSelection(selection);

            resetOnScrollListener();
        }
    }

    public CalendarSimpleDate getDate(int position) {
        return ((CalendarAdapter)getAdapter()).getItem(position);
    }

    private void setAdapterSelection(int selection) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSelectionFromTop(selection, 0);
        } else {
            setSelection(selection);
        }
    }

    // TODO find a better way to reset the listener after the view reloads and is scrolled to the correct location
    private void resetOnScrollListener() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setOnScrollListener(CalendarView.this);
            }
        }, 1000);
    }

    public class CalendarViewException extends RuntimeException {
        public CalendarViewException(String message) {
            super(message);
        }
    }
}
