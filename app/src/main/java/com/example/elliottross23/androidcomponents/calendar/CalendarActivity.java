package com.example.elliottross23.androidcomponents.calendar;

import android.app.Activity;
import android.os.Bundle;

import com.example.elliottross23.androidcomponents.R;

public class CalendarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.setAdapter(new CustomCalendarAdapter(CalendarActivity.this, 4, 2));
    }
}
