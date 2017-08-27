package com.example.sun.demo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sun on 17/8/22.
 */

public class ToastUtils {
    public static void toast(Context c, String s) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }
}
