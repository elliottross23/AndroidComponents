package com.example.elliottross23.androidcomponents.amountviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.elliottross23.androidcomponents.R;

/**
 * Created by elliottross on 10/4/15.
 */
public class PercentageCircleRelativeLayout extends RelativeLayout {
    private static final int START_ANGLE_POINT = 90;
    private final Paint painter = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint circlePainter = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rect;
    private float angle;
    private float backgroundAngle;
    private TextView textView;

    /* CUSTOM ATTRIBUTES */
    private boolean showBackgroundCircle;
    private int backgroundCircleColor;
    private int backgroundColor;
    private int circleColor;
    private int animationDuration;
    private int circleStrokeWidth;
    private int backgroundCircleStrokeWidth;
    private boolean showText;


    public PercentageCircleRelativeLayout(Context context) {
        super(context);
        init();
    }

    public PercentageCircleRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getCustomAttrs(context, attrs);
        init();
    }

    public PercentageCircleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getCustomAttrs(context, attrs);
        init();
    }

    private void getCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PercentageCircleRelativeLayout, 0, 0);
        try {
            showBackgroundCircle = ta.getBoolean(R.styleable.PercentageCircleRelativeLayout_showBackgroundCircle, true);
            circleColor = ta.getColor(R.styleable.PercentageCircleRelativeLayout_circleColor, Color.RED);
            backgroundCircleColor = ta.getColor(R.styleable.PercentageCircleRelativeLayout_backgroundCircleColor, Color.LTGRAY);
            backgroundColor = ta.getColor(R.styleable.PercentageCircleRelativeLayout_backgroundColor, Color.TRANSPARENT);
            animationDuration = ta.getInteger(R.styleable.PercentageCircleRelativeLayout_animDuration, 1000);
            circleStrokeWidth = ta.getInteger(R.styleable.PercentageCircleRelativeLayout_circleStrokeWidth, 50);
            backgroundCircleStrokeWidth = ta.getInteger(R.styleable.PercentageCircleRelativeLayout_backgroundCircleStrokeWidth, 40);
            showText = ta.getBoolean(R.styleable.PercentageCircleRelativeLayout_showText, true);
        } finally {
            ta.recycle();
        }

        setBackgroundColor(backgroundColor);
    }

    private void init() {
        setWillNotDraw(false); // Need this to update the view!
        painter.setStrokeWidth(circleStrokeWidth);

        Paint.Style paintStyle =  Paint.Style.STROKE;

        painter.setStyle(paintStyle);
        painter.setColor(circleColor);

        circlePainter.setStrokeWidth(backgroundCircleStrokeWidth);
        circlePainter.setStyle(paintStyle);
        circlePainter.setColor(backgroundCircleColor);

        //Initial Angle (optional, it can be zero)
        angle = 0;
        backgroundAngle = 0;

        if(showText) {
            textView = new TextView(getContext());
            RelativeLayout.LayoutParams textViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
            textView.setGravity(Gravity.CENTER);
            this.addView(textView, textViewLayoutParams);
        }
    }

    /**
     * 1. Creates textview to show the percent in the middle
     * 2. starts an animation to fade in the textview
     * 3. When that animation is done, then draw the circle layout
     * @param percent
     */
    public void showPercentDone(final int percent) {
        if(showText) {
            textView.setText(String.valueOf(percent) + "%" + "\n" + "complete");
        }
        ViewAnimation viewAnimation = new ViewAnimation(angle, percent);
        viewAnimation.setDuration(animationDuration);
        startAnimation(viewAnimation);
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setBackgroundAngle(float angle) {
        this.backgroundAngle = angle;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int left, top, right, bottom, measurement, circleWidth = 0;
        int height = getHeight();
        int width = getWidth();

        if(width < height) {
            // Use width as a basis
            measurement = width;
        } else {
            // Use height as a basis
            measurement = height;
        }

        top = (int) painter.getStrokeWidth() + 20;
        bottom = measurement - top;
        circleWidth = bottom - top;
        left = (width - circleWidth)/2;
        right = left + circleWidth;

        rect = new RectF(left, top, right, bottom);

        if(showBackgroundCircle) {
            canvas.drawArc(rect, START_ANGLE_POINT, backgroundAngle, false, circlePainter);
        }

        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, painter);
    }

    private class ViewAnimation extends Animation {
        private float oldAngle;
        private float newAngle;
        private float newBackgroundAngle;

        public ViewAnimation(float angle, int percentage) {
            this.oldAngle = angle;
            this.newAngle = ((float)360) * (percentage/100f);
            this.newBackgroundAngle = 920; //going around more than once to speed it up
        }
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation transformation) {
            float angleToPass = oldAngle + ((newAngle - oldAngle) * interpolatedTime);
            float backgroundAngleToPass = oldAngle + ((newBackgroundAngle - oldAngle) * interpolatedTime);
            setAngle(angleToPass);
            setBackgroundAngle(backgroundAngleToPass);
            requestLayout();
        }
    }



}
