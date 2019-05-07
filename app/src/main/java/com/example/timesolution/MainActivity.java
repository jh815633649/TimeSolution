package com.example.timesolution;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private NavigationView left_navView;
    private View headView;
    private ImageButton userImg;
    private TextView userName, userDesc;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showNav(R.id.navigation_home);
                    return true;
                case R.id.navigation_dashboard:
                    showNav(R.id.navigation_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    showNav(R.id.navigation_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //左侧导航栏
        left_navView = findViewById(R.id.nav);
        //导航栏head
        headView = left_navView.getHeaderView(0);
        userImg = headView.findViewById(R.id.userImg);
        userName = headView.findViewById(R.id.userName);
        userDesc = headView.findViewById(R.id.userDesc);
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录页面
                //Toast.makeText(getApplicationContext(), "用户登录", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
        //左侧导航栏菜单监听
        left_navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.setting:
                        Toast.makeText(getApplicationContext(), "设置", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    //init（）用来初始化组件
    private void init() {
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();

        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        //开启一个事务将fragment动态加载到组件
        beginTransaction.add(R.id.content, fragmentOne)
                .add(R.id.content, fragmentTwo)
                .add(R.id.content, fragmentThree);
        //隐藏fragment
        beginTransaction.hide(fragmentOne)
                .hide(fragmentTwo)
                .hide(fragmentThree);
        //beginTransaction.addToBackStack(null);//返回到上一个显示的fragment
        beginTransaction.commit();//每一个事务最后操作必须是commit（），否则看不见效果.
        showNav(R.id.navigation_home);
    }

    private void showNav(int navid) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        switch (navid) {
            case R.id.navigation_home:
                beginTransaction.hide(fragmentTwo).hide(fragmentThree);
                beginTransaction.show(fragmentOne);
                //beginTransaction.addToBackStack(null);
                //Toast.makeText(this, "xdpi：" + getResources().getDisplayMetrics().xdpi, Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, "ydpi：" + getResources().getDisplayMetrics().ydpi, Toast.LENGTH_SHORT).show();
                beginTransaction.commit();
                break;
            case R.id.navigation_dashboard:
                beginTransaction.hide(fragmentOne).hide(fragmentThree);
                beginTransaction.show(fragmentTwo);
                //beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            case R.id.navigation_notifications:
                beginTransaction.hide(fragmentTwo).hide(fragmentOne);
                beginTransaction.show(fragmentThree);
                //beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
        }
    }

    @Override
    public void onResume(){
        SharedPreferences read = getSharedPreferences("current_user", MODE_PRIVATE);
        //return mPassword.equals(read.getString("password", ""));
        String existed=read.getString("current_username","");
        if(existed.equals("")){

        }
        else{
            userName.setText(existed);
        }
        super.onResume();
    }
}

