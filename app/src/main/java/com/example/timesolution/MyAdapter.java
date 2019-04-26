package com.example.timesolution;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jh on 2019/3/4.
 */
public class MyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<thingsToDo> mDatas;
    private Context thiscontext;

    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public MyAdapter(Context context, List<thingsToDo> datas) {

        thiscontext=context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    //返回数据集的长度
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void delThing(int position) {mDatas.remove(position);}

    //这个方法才是重点，我们要为它编写一个ViewHolder
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.thing_in_list, parent, false); //加载布局
            holder = new ViewHolder();

            holder.ibtn=(ImageButton)convertView.findViewById(R.id.imageButton) ;
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.desc = (TextView) convertView.findViewById(R.id.desc);

            convertView.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }

        thingsToDo thing = mDatas.get(position);
        holder.title.setText(thing.getTitle());
        holder.desc.setText(thing.getDesc());

        holder.ibtn.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               //todo
                                               Intent intent = new Intent(thiscontext, modifyThing.class);
                                               Bundle bundle=new Bundle();
                                               bundle.putInt("position",position);
                                               intent.putExtras(bundle);
                                               thiscontext.startActivity(intent);
                                           }
                                       }
        );
        return convertView;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        ImageButton ibtn;
        TextView title;
        TextView desc;
    }

}