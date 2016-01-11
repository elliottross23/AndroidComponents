package com.example.elliottross23.androidcomponents.amountviews;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elliottross23.androidcomponents.R;

public class CircleAmountActivity extends Activity {
    private PercentageCircleRelativeLayout percentageCircleRelativeLayout;
    private EditText percentageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_amount);
        // Used so we can tap the home button on the action bar to go back
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our views
        percentageCircleRelativeLayout = ((PercentageCircleRelativeLayout) findViewById(R.id.percentage_circle_view));
        percentageEditText = (EditText) findViewById(R.id.percentage_text_view);
        percentageEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    onUpdatePercentPressed(null);
                    return true;
                }

                return false;
            }
        });

        /* Just an example to show how it works when you enter the activity.
           We are waiting a full second before starting it. */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                percentageCircleRelativeLayout.showPercentDone(72);
            }
        }, 1000);
    }

    /**
     * Go back to the previous activity when the home button on the action bar
     * is pressed
     * @param item
     * @return true if the action is consumed, false otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Button press that lets you keep animating the view and watch
     * the changes happen
     * @param view not used
     */
    public void onUpdatePercentPressed(View view) {
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
