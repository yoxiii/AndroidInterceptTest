package com.example.intercepttest;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {
   // private ListView mListView;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyReceiver", "Received broadcast: " + intent.getAction());
      //  SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.intercepttest/databases/yoxi.db", null, SQLiteDatabase.OPEN_READWRITE);
        String outcallnumber=intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        SharedPreferences sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        String number=sp.getString("number","");
        //Cursor cursor=db.query("information",projection,selection,selectionArgs,null,null,null);
// 如果查询到匹配的号码，取消呼叫
        if(number.equals(number)){
            setResultData(null);
//
//保存拦截号码到数据库
            MyHelper myHelper=new MyHelper(context);
            SQLiteDatabase db=myHelper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("phonenum",number);

            Calendar c=Calendar.getInstance();
            Date date=c.getTime();
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
            String time=df.format(date);

            values.put("time",time);
//
//
//            MainActivity2intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(MainActivity2intent);

            db.insert("call", null,values);
        //关闭数据库和游标
        db.close();
//        if(cursor!=null)
//            cursor.close();

        Intent intent1=new Intent();
        intent1.setClass(context,MainActivity2.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);

        }
    }
}