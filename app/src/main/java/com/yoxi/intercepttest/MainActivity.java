package com.yoxi.intercepttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intercepttest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;

    MyHelper myHelper;
    String phoneNumber;


    MyReceiver receiver=new MyReceiver();
    List<Map<String,String>> datas;
    RecyclerView rv_list;
    String[] permissions={"android.permission.READ_PHONE_STATE",
    "android.permission.PROCESS_OUTGOING_CALLS",
    "android.permission.SYSTEM_ALERT_WINDOW",
    "android.permission.SYSTEM_OVERLAY_WINDOW"};
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态权限申请
        requestPermissions(permissions,1);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        registerReceiver(receiver,intentFilter);
        datas=new ArrayList<>();

       myHelper=new MyHelper(this);
        ContentValues values;

        SQLiteDatabase db;




        //实例化广播接收者
//        MyReceiver receiver = new MyReceiver();
//        String action = "android.provider.Telephony.SMS_RECEIVED";
//        IntentFilter intentfilter=new IntentFilter(action);
//
//        registerReceiver(receiver,intentfilter);
        Button btn_save=findViewById(R.id.et_ipnum1);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_ipnum=(EditText) findViewById(R.id.et_ipnum);
               number=et_ipnum.getText().toString().trim();
               // Intent intent = new Intent("android.intent.action.NEW_OUTGOING_CALL");
                SharedPreferences sp=getSharedPreferences("config",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();   //创建editor对象，保存用户输入的号码
                editor.putString("number",number);
                editor.commit();

            }
        });
        getDatas();
        rv_list=findViewById(R.id.recyclerview);
        rv_list.setAdapter(new myAdapter());
        rv_list.setLayoutManager(new LinearLayoutManager(this));

//        db=myHelper.getWritableDatabase();
//        values=new ContentValues();
//        values.put("phonenum",number);
//        long id=db.insert("information",null,values);
//        db.close();
//        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
//        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
  //        startActivity(intent);
    }

    //按钮保存号码
        //获取用户输入的拦截号码


    class myAdapter extends RecyclerView.Adapter<myHolders>{

        @NonNull
        @Override
        public myHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item_layout,parent,false);
            myHolders holders=new myHolders(view);
            return holders;
        }

        @Override
        public void onBindViewHolder(@NonNull myHolders holder, int position) {
            holder.tv_num.setText(datas.get(position).get("number"));
            holder.tv_time.setText(datas.get(position).get("time"));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }
    class myHolders extends  RecyclerView.ViewHolder{

        TextView tv_num;
        TextView tv_time;
        public myHolders(@NonNull View itemView) {
            super(itemView);
            tv_num=itemView.findViewById(R.id.tv_num);
            tv_time=itemView.findViewById(R.id.tv_time);
        }
    }

    private void getDatas(){
        SQLiteDatabase db=myHelper.getReadableDatabase();
       Cursor cursor= db.query("call",null,null,null,null,null,null);
       //循环查询下一位
        while (cursor.moveToNext()){
            Map<String,String> map=new HashMap<>();
            String number=cursor.getString(1);
            String time=cursor.getString(2);
            map.put("number",number);
            map.put("time",time);
        }
        db.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}