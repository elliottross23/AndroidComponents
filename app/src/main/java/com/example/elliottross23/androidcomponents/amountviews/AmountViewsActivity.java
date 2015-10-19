package com.example.elliottross23.androidcomponents.amountviews;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.elliottross23.androidcomponents.R;

public class AmountViewsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_views);

        findViewById(R.id.circle_percent_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AmountViewsActivity.this, CircleAmountActivity.class));
            }
        });
    }
}
