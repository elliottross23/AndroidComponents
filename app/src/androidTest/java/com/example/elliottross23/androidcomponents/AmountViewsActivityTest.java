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
public class AmountViewsActivityTest extends ActivityUnitTestCase<AmountViewsActivity> {
    private Button circleLayoutButton;
    private Button lineLayoutButton;

    public AmountViewsActivityTest() {
        super(AmountViewsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Intent launchIntent = new Intent(getInstrumentation().getTargetContext(), AmountViewsActivity.class);
        startActivity(launchIntent, null, null);

        circleLayoutButton = (Button) getActivity().findViewById(R.id.circle_percent_button);
        lineLayoutButton = (Button) getActivity().findViewById(R.id.line_percent_button);
    }

    @SmallTest
    public void testButtonsVisible() {
        assertNotNull(circleLayoutButton);
        assertEquals(View.VISIBLE, circleLayoutButton.getVisibility());
        assertNotNull(lineLayoutButton);
        assertEquals(View.VISIBLE, lineLayoutButton.getVisibility());
    }

    @MediumTest
    public void testCirclePercentButtonGoesToNextActivity() {
        circleLayoutButton.performClick();
        final Intent startedActivityIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", startedActivityIntent);
    }

    @MediumTest
    public void testLineButtonGoesToNextActivity() {
        lineLayoutButton.performClick();
        final Intent startedActivityIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", startedActivityIntent);
    }
}
