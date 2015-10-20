package com.example.elliottross23.androidcomponents.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by elliottross on 8/17/15.
 */
public class SystemUtils {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);

        if(imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
