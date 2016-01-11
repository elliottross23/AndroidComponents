package com.example.elliottross23.androidcomponents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elliottross23.androidcomponents.amountviews.AmountViewsActivity;
import com.example.elliottross23.androidcomponents.calendar.CalendarActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGoToAmountViewPressed(View v) {
        startActivity(new Intent(MainActivity.this, AmountViewsActivity.class));
    }

    public void onGoToCalendarViewPressed(View v) {
        startActivity(new Intent(MainActivity.this, CalendarActivity.class));
    }
}
