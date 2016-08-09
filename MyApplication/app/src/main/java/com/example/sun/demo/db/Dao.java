package com.example.sun.demo.db;

import android.content.Context;

/**
 * Created by sun on 16/8/8.
 */
public class Dao {
    private DbHelper dbHelper;
    private static Dao instance;

    public static Dao getInstance(Context context) {
        if (null == instance) {
            synchronized (Dao.class) {
                if (null == instance) {
                    instance = new Dao(context);
                }
            }
        }
        return instance;
    }

    public Dao(Context context) {
        dbHelper = new DbHelper(context);
    }

}
