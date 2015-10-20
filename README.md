# AndroidComponents

Putting out the custom components that  I build for my at-home development. 
All projects are licensed under the MIT license.

### PercentageCircleRelativeLayout 
-  a custom view that draws a background circle and colored circle that shows a percentage. All you need to do is add it to your layout, and in your code get a reference to it and call showPercentDone(int percent). This will redraw the view with animation.

- How to add to your project:
1. Add to your layout.xml file (All attributes prefixed with "app:" are customizable)
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
  app:backgroundColor="@android:color/transparent">
  </com.example.elliottross23.androidcomponents.amountviews.PercentageCircleRelativeLayout>
```
2. In your Activity, get a reference to the view and set the percentage
```
percentageCircleRelativeLayout = ((PercentageCircleRelativeLayout) findViewById(R.id.percentage_circle_view));
percentageCircleRelativeLayout.showPercentDone(72);
```

### PercentageLineRelativeLayout (in progress)
- similar to the circle but a line instead. Still working on the implementation for this one. 
