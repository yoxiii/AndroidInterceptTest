package com.example.intercepttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {
//    public List<String> phonenumList;
//
//    private ListView mListView;
//    ArrayList<String> phonenumlist=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
//        mListView=(ListView) findViewById(R.id.lv);
//        // 获取传递过来的数据
//        Intent intent=getIntent();
//       ArrayList<String> phonenumlist=intent.getStringArrayListExtra("phonenumList");
//        phonenumList=retrievePhonenumDataFromDatabase(context);
//        // 使用 phonenumList 填充 ListView
//        if (phonenumlist != null) {
////        phonenumlist.add("12");
//        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.list_item_layout,R.id.phonenumTextView,phonenumlist);
//        mListView.setAdapter(adapter);}
    }
//    private List<String> retrievePhonenumDataFromDatabase(Context context) {
//        // 在这里执行数据库查询，并将phonenum数据存储到一个List中
//        List<String> phonenumList = new ArrayList<>();
//        // 执行数据库查询，并将结果添加到phonenumList
//        // 例如，使用数据库助手类获取数据库实例，然后查询数据库表
//        return phonenumList;
//
//    }
}