package com.example.elliottross23.androidcomponents.amountviews;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
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

import com.example.elliottross23.androidcomponents.R;

/**
 * Created by elliottross on 10/16/15.
 */
public class PercentageLineRelativeLayout extends RelativeLayout {
    private final Paint painter = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint backgroundLinePainter = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int amount;
    private TextView textView;

    /* CUSTOM ATTRIBUTES */
    private boolean showBackgroundLine;
    private int backgroundLineColor;
    private int backgroundColor;
    private int lineColor;
    private int animationDuration;
    private int lineStrokeWidth;
    private int backgroundLineStrokeWidth;
    private boolean showText;

    public PercentageLineRelativeLayout(Context context) {
        super(context);
        init();
    }

    public PercentageLineRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getCustomAttrs(context, attrs);
        init();
    }

    public PercentageLineRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getCustomAttrs(context, attrs);
        init();
    }

    private void getCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PercentageLineRelativeLayout, 0, 0);
        try {
            showBackgroundLine = ta.getBoolean(R.styleable.PercentageLineRelativeLayout_showBackgroundLine, true);
            lineColor = ta.getColor(R.styleable.PercentageLineRelativeLayout_lineColor, Color.RED);
            backgroundLineColor = ta.getColor(R.styleable.PercentageLineRelativeLayout_backgroundLineColor, Color.LTGRAY);
            backgroundColor = ta.getColor(R.styleable.PercentageLineRelativeLayout_backgroundColor, Color.TRANSPARENT);
            animationDuration = ta.getInteger(R.styleable.PercentageLineRelativeLayout_animDuration, 1000);
            lineStrokeWidth = ta.getInteger(R.styleable.PercentageLineRelativeLayout_lineStrokeWidth, 50);
            backgroundLineStrokeWidth = ta.getInteger(R.styleable.PercentageLineRelativeLayout_backgroundLineStrokeWidth, 40);
            showText = ta.getBoolean(R.styleable.PercentageLineRelativeLayout_showText, true);
        } finally {
            ta.recycle();
        }

        setBackgroundColor(backgroundColor);
    }

    private void init() {
        setWillNotDraw(false);
        painter.setStrokeWidth(lineStrokeWidth);
        painter.setStyle(Paint.Style.STROKE);
        painter.setColor(lineColor);

        backgroundLinePainter.setStrokeWidth(backgroundLineStrokeWidth);
        backgroundLinePainter.setStyle(Paint.Style.STROKE);
        backgroundLinePainter.setColor(backgroundLineColor);

        // Initial
        amount = 0;

        if(showText) {
            textView = new TextView(getContext());
            RelativeLayout.LayoutParams textViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
            textView.setGravity(Gravity.CENTER);
            addView(textView, textViewLayoutParams);
        }
    }

    /**
     * 1. Creates textview to show the percent in the middle
     * 3. Animate drawing the line
     * @param percentDone
     */
    public void showPercentDone(final int percentDone) {
        if(showText) {
            textView.setText(String.valueOf(percentDone) + "%");
        }
        ViewAnimation viewAnimation = new ViewAnimation(amount, percentDone);
        viewAnimation.setDuration(animationDuration);
        startAnimation(viewAnimation);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int startX, yValue, stopX;
        int measurement = getWidth();

        startX = 0;
        yValue = measurement/4;
        stopX = measurement;
        float colorLineStopX = ((float)amount/100f) * measurement;

        if(showBackgroundLine) {
            canvas.drawLine(startX, yValue, stopX, yValue, backgroundLinePainter);
        }

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
