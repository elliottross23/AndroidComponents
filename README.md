# AndroidComponents

Putting out the custom components that  I build for my at-home development. 
All projects are licensed under the MIT license.

**NOTE: These components are under development and will be labeled when they have been completely tested and ready for production use.**

Table of Contents:

1. Custom Views
  - [PercentageCircleRelativeLayout](#percentagecirclerelativelayout)
  - [PercentageLineRelativeLayout](#percentagelinerelativelayout)
2. Utils
  - [SystemUtils](#systemutils)

### PercentageCircleRelativeLayout 
-  a custom view that draws a background circle and colored circle that shows a percentage. All you need to do is add it to your layout, and in your code get a reference to it and call showPercentDone(int percent). This will redraw the view with animation.

- Add to your layout.xml file (All attributes prefixed with "app:" are customizable)
```
  <com.example.elliottross23.androidcomponents.amountviews.PercentageCircleRelativeLayout
      android:id="@+id/percentage_circle_view"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:layout_alignParentTop="true"
      android:layout_above="@+id/center_line"
      app:animDuration="4000"
      app:circleColor="@android:color/black"
      app:showBackgroundCircle="true"
      app:backgroundCircleColor="@android:color/darker_gray"
      app:backgroundColor="@android:color/transparent"
      app:circleStrokeWidth="50"
      app:backgroundCircleStrokeWidth="40">
  </com.example.elliottross23.androidcomponents.amountviews.PercentageCircleRelativeLayout>
```
- Then in your Activity, get a reference to the view and set the percentage
```
percentageCircleRelativeLayout = ((PercentageCircleRelativeLayout) findViewById(R.id.percentage_circle_view));
percentageCircleRelativeLayout.showPercentDone(72);
```

### PercentageLineRelativeLayout
- similar to the circle but a line instead.

- Add to your layout.xml file (All attributes prefixed with "app:" are customizable)
```
  <com.example.elliottross23.androidcomponents.amountviews.PercentageLineRelativeLayout
      android:id="@+id/percentage_line_view"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:layout_above="@+id/center_line"
      app:animDuration="1000"
      app:lineColor="@android:color/holo_blue_dark"
      app:showBackgroundLine="true"
      app:backgroundLineColor="@android:color/darker_gray"
      app:backgroundColor="@android:color/transparent"
      app:lineStrokeWidth="60"
      app:backgroundLineStrokeWidth="40"
      app:showText="true">
  </com.example.elliottross23.androidcomponents.amountviews.PercentageLineRelativeLayout>
```

- Then in your Activity, get a reference to the view and set the percentage
```
percentageLineRelativeLayout = ((PercentageLineRelativeLayout) findViewById(R.id.percentage_line_view));
percentageLineRelativeLayout.showPercentDone(72);
```

### SystemUtils
(a collection of utils for the android system)
- hideKeyboard(Activity activity)
