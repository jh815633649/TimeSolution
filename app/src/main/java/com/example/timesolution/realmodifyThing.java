package com.example.timesolution;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class realmodifyThing extends AppCompatActivity {

    private DateLine startline, deadline;
    private List<thingsToDo> mDate;
    private thingsToDo thisThing;
    private EditText editText1, //title
            editText2, //desc
            editText3, //impt
            editText4; //tit
    private Spinner spinner1, //stl.date
            spinner2, //stl.time
            spinner3, //ddl.date
            spinner4; //ddl.time
    private ArrayAdapter adapter1, adapter2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realmodify_thing);

        //得到事件
        mDate = FragmentOne.getList();//得到事件列表
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final int position = bundle.getInt("position");//得到事件编号
        thisThing = mDate.get(position);//得到事件

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        startline = new DateLine();
        deadline = new DateLine();

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        spinner1 = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        btn = findViewById(R.id.button2);

        //下拉框适配器
        adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getDateSource());
        spinner1.setAdapter(adapter1);
        spinner3.setAdapter(adapter1);

        adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getTimeSource());
        spinner2.setAdapter(adapter2);
        spinner4.setAdapter(adapter2);

        //下拉框默认值
        spinner1.setSelection(thisThing.get_line_Date(1));
        spinner2.setSelection(thisThing.get_line_Time(1));
        spinner3.setSelection(thisThing.get_line_Date(2));
        spinner4.setSelection(thisThing.get_line_Time(2));
        //输入框默认值
        editText1.setText(thisThing.getTitle());
        editText2.setText(thisThing.getDesc());
        editText3.setText(String.valueOf(thisThing.getImpt()));
        editText4.setText(String.valueOf(thisThing.getTit()));
        //fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //下拉框监听
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                startline.date = parent.getItemAtPosition(position).toString();
                //Toast.makeText(AddThing.this, "你点击的是:"+str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                startline.date="无";
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                startline.time = parent.getItemAtPosition(position).toString();
                //Toast.makeText(AddThing.this, "你点击的是:"+str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                startline.time="无";
            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                deadline.date = parent.getItemAtPosition(position).toString();
                //Toast.makeText(AddThing.this, "你点击的是:"+str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                deadline.date="无";
            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                deadline.time = parent.getItemAtPosition(position).toString();
                //Toast.makeText(AddThing.this, "你点击的是:"+str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                deadline.time="无";
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       /*
                                       startline.date=spinner1.getSelectedItem().toString();
                                       startline.time=spinner2.getSelectedItem().toString();
                                       deadline.date=spinner3.getSelectedItem().toString();
                                       deadline.time=spinner4.getSelectedItem().toString();
                                       */
                                       /*
                                       //文件修改
                                       String temp_name = editText1.getText().toString();
                                       SharedPreferences read = getSharedPreferences(temp_name, MODE_PRIVATE);
                                       SharedPreferences.Editor editor = read.edit();
                                       editor.putString("title", editText1.getText().toString());
                                       editor.putString("desc", editText2.getText().toString());
                                       editor.putString("impt", editText3.getText().toString());
                                       editor.putString("startline.date", startline.date);
                                       editor.putString("startline.time", startline.time);
                                       editor.putString("deadline.date", deadline.date);
                                       editor.putString("deadline.time", deadline.time);
                                       editor.putString("tit", editText4.getText().toString());
                                       editor.apply();
                                       */
                                       //mData修改
                                       FragmentOne.delT(position);
                                       FragmentOne.addT(new thingsToDo(
                                               editText1.getText().toString(),
                                               editText2.getText().toString(),
                                               startline,
                                               deadline,
                                               editText3.getText().toString(),
                                               editText4.getText().toString()
                                       ));

                                       finish();
                                   }
                               }
        );
    }

    public List<String> getDateSource() {
        List<String> list = new ArrayList<String>();
        list.add("无");
        list.add("周一");
        list.add("周二");
        list.add("周三");
        list.add("周四");
        list.add("周五");
        list.add("周六");
        list.add("周日");
        return list;
    }

    public List<String> getTimeSource() {
        List<String> list = new ArrayList<String>();
        list.add("无");
        list.add("7:00");  //英文冒号
        list.add("7:30");
        list.add("8:00");
        list.add("8:30");
        list.add("9:00");
        list.add("9:30");
        list.add("10:00");
        list.add("10:30");
        list.add("11:00");
        list.add("11:30");
        list.add("12:00");
        list.add("12:30");
        list.add("13:00");
        list.add("13:30");
        list.add("14:00");
        list.add("14:30");
        list.add("15:00");
        list.add("15:30");
        list.add("16:00");
        list.add("16:30");
        list.add("17:00");
        list.add("17:30");
        list.add("18:00");
        list.add("18:30");
        list.add("19:00");
        list.add("19:30");
        list.add("20:00");
        list.add("20:30");
        list.add("21:00");
        list.add("21:30");
        list.add("22:00");
        list.add("22:30");
        list.add("23:00");
        return list;
    }

    //返回键
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
