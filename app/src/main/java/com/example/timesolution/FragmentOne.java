package com.example.timesolution;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends Fragment {

    private View mView;
    private ImageButton ibtn;
    private TextView text_empty;
    private ListView listView;
    private static List<thingsToDo> mDatas;
    private static MyAdapter myAdapter;
    private int rqCode = 0;

    private DateLine startline, deadline;
    private String impt;
    private String tit;
    private String title, desc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        //初始化页面和数据
        initView();
        initData();
        //判空及显示提示
        if(mDatas.size()!=0){
            text_empty.setVisibility(View.GONE);
        }
        ibtn = mView.findViewById(R.id.button);
        ibtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //添加事件跳转页面
                                        Intent intent = new Intent(getActivity(), AddThing.class);
                                        startActivityForResult(intent, rqCode);
                                        //getActivity().overridePendingTransition(R.anim.slide, R.anim.ani_left_sign_out);
                                    }
                                }
        );
        return mView;
    }

    //返回页面时接收传来的参数
    @Override
    public void onActivityResult(int rqCode, int resultCode, Intent intent) {
        if (resultCode == getActivity().RESULT_OK) {
            Bundle bundle = intent.getExtras();
            title = bundle.getString("title", "");
            desc = bundle.getString("desc", "");
            impt = bundle.getString("impt", "");
            //下面两行的写法很重要
            startline = new DateLine(bundle.getString("startline.date", ""), bundle.getString("startline.time", ""));
            deadline = new DateLine(bundle.getString("deadline.date", ""), bundle.getString("deadline.time", ""));
            tit = bundle.getString("tit", "");

            mDatas.add(new thingsToDo(title, desc, startline, deadline, impt, tit));
            text_empty.setVisibility(View.GONE);//无事件提示隐藏
            myAdapter = new MyAdapter(this.getActivity(), mDatas);
            listView.setAdapter(myAdapter);
        } else {
            //什么也不做
        }
    }

    //方法：初始化View
    private void initView() {
        listView = (ListView) mView.findViewById(R.id.listview);
        text_empty = mView.findViewById(R.id.text_empty);
    }

    //方法；初始化Data
    private void initData() {
        startline = new DateLine();
        deadline = new DateLine();

        mDatas = new ArrayList<thingsToDo>();

        //将数据装到集合中去
        //thingsToDo thing = new thingsToDo();
        //mDatas.add(thing);

        //为数据绑定适配器
        myAdapter = new MyAdapter(this.getActivity(), mDatas);

        listView.setAdapter(myAdapter);
    }

    //接口，传送事件列表给其他组件
    public static List<thingsToDo> getList() {
        return mDatas;
    }
    //删除某事件
    public static void delT(int position){myAdapter.delThing(position);}
    //增加新事件
    public static void addT(thingsToDo t){mDatas.add(t);}

    @Override
    public void onResume(){
        if(mDatas.size()==0){
            text_empty.setVisibility(View.VISIBLE);
        }
        myAdapter = new MyAdapter(this.getActivity(), mDatas);
        listView.setAdapter(myAdapter);
        super.onResume();
    }
}
