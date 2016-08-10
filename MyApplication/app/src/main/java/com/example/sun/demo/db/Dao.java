package com.example.sun.demo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sun.demo.util.Constant;

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

    private void closeDB(SQLiteDatabase db) {
        db.close();
    }

    private void closeCursor(Cursor cursor) {
        cursor.close();
    }


    public synchronized void insertPersonData(String name, int age) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        db.insert(Constant.FIRST_TABLE, null, values);
        closeDB(db);
    }

    public synchronized int getPersonNum(String name, int age) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Constant.FIRST_TABLE, new String[]{"age", "name"}, "age = ? and name = ?", new String[]{String.valueOf(age), name}, null, null, null);
        int num = cursor.getCount();
        closeDB(db);
        closeCursor(cursor);
        return num;
    }

}
