package com.example.elliottross23.androidcomponents.amountviews;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elliottross23.androidcomponents.R;

public class CircleAmountActivity extends Activity {
    private PercentageCircleRelativeLayout percentageCircleRelativeLayout;
    private EditText percentageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_amount);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        percentageCircleRelativeLayout = ((PercentageCircleRelativeLayout) findViewById(R.id.percentage_circle_view));
        percentageEditText = (EditText) findViewById(R.id.percentage_text_view);

        // Just an example to delay when the circle animates
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                percentageCircleRelativeLayout.showPercentDone(72);
            }
        }, 1000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onUpdatePercentPressed(View v) {
        int percent = 0;
        try {
            percent = Integer.parseInt(percentageEditText.getText().toString());
        } catch(NumberFormatException nfe) {
            Toast.makeText(CircleAmountActivity.this, "Invalid percent", Toast.LENGTH_SHORT).show();
        }

        if(percent >= 0 && percent <= 100) {
            percentageCircleRelativeLayout.showPercentDone(percent);
        }
    }

}
