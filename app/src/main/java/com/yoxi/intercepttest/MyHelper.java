package com.yoxi.intercepttest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    private final String db_name="yoxi.db";
    private final int version=1;
    public MyHelper(Context context) {
        super(context, "yoxi.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE call(_id INTEGER PRIMARY KEY AUTOINCREMENT,phonenum VARCHAR(20),time VARCHAR(40))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
