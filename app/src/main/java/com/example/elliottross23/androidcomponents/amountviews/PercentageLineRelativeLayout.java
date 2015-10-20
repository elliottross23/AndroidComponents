package com.example.elliottross23.androidcomponents.amountviews;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by elliottross on 10/16/15.
 */
public class PercentageLineRelativeLayout extends RelativeLayout {
    private final Paint painter = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint backgroundLinePainter = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int amount;
    private TextView textView;

    public PercentageLineRelativeLayout(Context context) {
        super(context);
        init();
    }

    public PercentageLineRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PercentageLineRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        painter.setStrokeWidth(50);
        painter.setStyle(Paint.Style.STROKE);
        painter.setColor(Color.RED);

        backgroundLinePainter.setStrokeWidth(40);
        backgroundLinePainter.setStyle(Paint.Style.STROKE);
        backgroundLinePainter.setColor(Color.LTGRAY);

        // Initial
        amount = 0;
        textView = new TextView(getContext());
        RelativeLayout.LayoutParams textViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
        textView.setGravity(Gravity.CENTER);
        addView(textView, textViewLayoutParams);
    }

    /**
     * 1. Creates textview to show the percent in the middle
     * 3. Animate drawing the line
     * @param percentDone
     */
    public void showPercentDone(final int percentDone) {
        textView.setText(String.valueOf(percentDone) + "%");
        ViewAnimation viewAnimation = new ViewAnimation(amount, percentDone);
        viewAnimation.setDuration(1000);
        startAnimation(viewAnimation);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int startX, yValue, stopX, measurement;
        int height = getHeight();
        int width = getWidth();

        if(width < height) {
            // Use width as a basis
            measurement = width;
        } else {
            // Use height as a basis
            measurement = height;
        }

        startX = 0;
        yValue = measurement/2;
        stopX = measurement;
        float colorLineStopX = ((float)amount/100f) * measurement;

        canvas.drawLine(startX, yValue, stopX, yValue, backgroundLinePainter);
        canvas.drawLine(startX, yValue, colorLineStopX, yValue, painter);
    }

    private class ViewAnimation extends Animation {
        private float oldAmount;
        private float newAmount;

        public ViewAnimation(int currentPercentage, int newPercentage) {
            this.oldAmount = currentPercentage;
            this.newAmount = (int)(((float)newPercentage/100f)* 100);
        }
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation transformation) {
            int amountToPass = (int) (oldAmount + ((newAmount - oldAmount) * interpolatedTime));
            setAmount(amountToPass);
            requestLayout();
        }
    }
}
