package com.example.elliottross23.androidcomponents.calendar;

import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.elliottross23.androidcomponents.R;
import com.example.elliottross23.androidcomponents.calendar.internals.CalendarAdapter;


/**
 * Created by elliottross on 1/10/16.
 */
public class CustomCalendarAdapter extends CalendarAdapter {
    private int dayHeight;

    public CustomCalendarAdapter(Activity activity, int numRowsVisible, int numberOfColumns) {
        super(activity, numRowsVisible, numberOfColumns);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        dayHeight = (displayMetrics.heightPixels / numRowsVisible);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent, CalendarSimpleDate date) {
        ViewHolder viewHolder;

        if(null == convertView) {
            convertView = activity.getLayoutInflater().inflate(R.layout.grid_item_calendar, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.dayNameTextView = (TextView) convertView.findViewById(R.id.grid_item_day_name);
            viewHolder.dayNumberTextView = (TextView) convertView.findViewById(R.id.grid_item_day_number);
            viewHolder.backgroundView = (RelativeLayout) convertView.findViewById(R.id.grid_item_background);
            viewHolder.pictureView = (ImageView) convertView.findViewById(R.id.grid_item_picture);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String monthName = "";

        if(isToday(date)) {
            viewHolder.backgroundView.setBackgroundColor(Color.GREEN);
        } else {
            viewHolder.backgroundView.setBackgroundColor(Color.TRANSPARENT);
        }

        // show month on first day
        if(date.getDayOfMonth() == 1) {
            monthName = CalendarUtils.getMonthShortName(date.getMonthOfYear()) + " ";
        }

        viewHolder.dayNameTextView.setText(date.getDayName());
        viewHolder.dayNumberTextView.setText(monthName + String.valueOf(date.getDayOfMonth()));
        viewHolder.backgroundView.setMinimumHeight(dayHeight);
//      TODO viewHolder.pictureView.setimage

        return convertView;
    }

    static class ViewHolder {
        TextView dayNameTextView;
        TextView dayNumberTextView;
        RelativeLayout backgroundView;
        ImageView pictureView;
    }

}
