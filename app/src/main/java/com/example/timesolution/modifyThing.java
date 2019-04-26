package com.example.timesolution;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class modifyThing extends AppCompatActivity {

    private List<thingsToDo> mDate;
    private thingsToDo thisThing;
    private Button btn_modify;
    private Button btn_yes;
    private Button btn_delete;
    private TextView tv_title;
    private TextView tv_stl;
    private TextView tv_ddl;
    private TextView tv_tit;
    private TextView tv_impt;
    private TextView tv_enmg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_thing);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_modify);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        mDate = FragmentOne.getList();//得到事件列表
        btn_modify = findViewById(R.id.btn_modify);
        btn_yes = findViewById(R.id.确定);
        btn_delete = findViewById(R.id.删除);
        tv_title = findViewById(R.id.text_title);
        tv_stl = findViewById(R.id.开始时间2);
        tv_ddl = findViewById(R.id.结束时间2);
        tv_tit = findViewById(R.id.所需时间2);
        tv_impt = findViewById(R.id.重要程度2);
        tv_enmg = findViewById(R.id.紧急程度2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final int position = bundle.getInt("position");//得到事件编号
        thisThing = mDate.get(position);

        tv_title.setText(thisThing.getTitle());
        tv_stl.setText(thisThing.getstl());
        tv_ddl.setText(thisThing.getddl());
        tv_tit.setText(String.valueOf(thisThing.getTit()));
        tv_impt.setText(String.valueOf(thisThing.getImpt()));
        tv_enmg.setText(String.valueOf(thisThing.getEnmg()));

        btn_modify.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Intent intent1=new Intent(v.getContext(),realmodifyThing.class);
                                              Bundle bundle1=new Bundle();
                                              bundle1.putInt("position",position);
                                              intent1.putExtras(bundle1);
                                              startActivity(intent1);
                                              finish();
                                          }
                                      }
        );
        btn_yes.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           finish();
                                       }
                                   }
        );
        btn_delete.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              new AlertDialog.Builder(v.getContext())
                                                      .setTitle("请确认")
                                                      .setMessage("要删除此事件吗？")
                                                      .setPositiveButton("是", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                                          @Override
                                                          public void onClick(DialogInterface dialogInterface, int i) {
                                                              FragmentOne.delT(position);
                                                              finish();
                                                          }
                                                      })
                                                      .setNegativeButton("否", null)
                                                      .create()
                                                      .show();
                                          }
                                      }
        );
    }

    //返回键
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        tv_title.setText(thisThing.getTitle());
        tv_stl.setText(thisThing.getstl());
        tv_ddl.setText(thisThing.getddl());
        tv_tit.setText(String.valueOf(thisThing.getTit()));
        tv_impt.setText(String.valueOf(thisThing.getImpt()));
        tv_enmg.setText(String.valueOf(thisThing.getEnmg()));
        super.onResume();
    }
}
