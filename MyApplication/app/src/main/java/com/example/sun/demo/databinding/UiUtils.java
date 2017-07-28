package com.example.sun.demo.databinding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by sun on 17/7/25.
 */

public class UiUtils {
    @BindingAdapter({"bind:stringArray", "bind:textLevel"})
    public static void setStringLevel(TextView textView, String[] stringArray, int textLevel) {
        textView.setText(stringArray[textLevel]);
    }
}
