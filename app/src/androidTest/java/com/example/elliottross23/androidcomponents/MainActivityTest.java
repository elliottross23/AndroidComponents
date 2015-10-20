package com.example.elliottross23.androidcomponents;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Button;

import com.example.elliottross23.androidcomponents.amountviews.AmountViewsActivity;

/**
 * Created by elliottross on 10/19/15.
 */
public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Intent launchIntent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(launchIntent, null, null);
    }

    @SmallTest
    public void testAmountViewsButtonHandle() {
        getActivity().onGoToAmountViewPressed(null);
        final Intent startedActivityIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", startedActivityIntent);
    }
}
