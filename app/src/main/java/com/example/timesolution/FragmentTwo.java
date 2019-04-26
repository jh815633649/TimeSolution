package com.example.timesolution;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class FragmentTwo extends Fragment {

    private View mView;
    private ImageButton ibtn;
    private RelativeLayout rl1,rl2,rl3,rl4,rl5,rl6,rl7;
    private int ThingTable[][]=new int[7][32];
    protected float pheight; //每格的标准高，即两小时
    int h; //relativeLayout的高度！
    private List<thingsToDo> mDate;
    private int ranColor[]={0xaa66ff66,0xaa20B2AA, 0xaa8B8B00, 0xaaEEAD0E,0xaaA3A3A3};
    private void addNewView(thingsToDo thisThing,int rl,int h,int l) //h是方块长度，l是距离顶端的距离
    {
        int Tcolor=ranColor[thisThing.getRanColor()];
        TextView v1=new TextView(getActivity());
        v1.setBackgroundColor(Tcolor);//颜色
        v1.setText(thisThing.getTitle());

        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,h);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.topMargin=l;
        v1.setLayoutParams(params);
        switch(rl)
        {
            case 1:rl1.addView(v1);break;
            case 2:rl2.addView(v1);break;
            case 3:rl3.addView(v1);break;
            case 4:rl4.addView(v1);break;
            case 5:rl5.addView(v1);break;
            case 6:rl6.addView(v1);break;
            case 7:rl7.addView(v1);break;
        }
        v1.setOnClickListener(new View.OnClickListener()
                              {
                                  @Override
                                  public void onClick(View v)
                                  {
                                      //todo
                                      v.setBackgroundColor(0xaa662200);
                                  }
                              }
        );
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_fragment_two, container, false);

        rl1=mView.findViewById(R.id.monday);
        rl2=mView.findViewById(R.id.tuesday);
        rl3=mView.findViewById(R.id.wednesday);
        rl4=mView.findViewById(R.id.thursday);
        rl5=mView.findViewById(R.id.friday);
        rl6=mView.findViewById(R.id.saturday);
        rl7=mView.findViewById(R.id.sunday);
        ibtn =mView.findViewById(R.id.button3);

        ibtn.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        //**排序
                                        //初始化
                                        rl1.removeAllViews();
                                        rl2.removeAllViews();
                                        rl3.removeAllViews();
                                        rl4.removeAllViews();
                                        rl5.removeAllViews();
                                        rl6.removeAllViews();
                                        rl7.removeAllViews();
                                        mDate=FragmentOne.getList();
                                        if(mDate.size()<1) return;
                                        //设置新p值
                                        for(int i=0;i<mDate.size();i++) {
                                            mDate.get(i).setp();
                                        }
                                        for(int i=0;i<7;i++)
                                            for(int j=0;j<32;j++)
                                                ThingTable[i][j]=0;
                                        //排序关键部分
                                        int []a=new int[mDate.size()];
                                        for(int i=0;i<mDate.size();i++) a[i]=-1;
                                        for(int i=0;i<mDate.size();i++)
                                        {
                                            int temp=1000,latter,t=-1;
                                            if(i==0) latter=0;
                                            else latter=mDate.get(a[i-1]).getP();
                                            for(int j=0;j<mDate.size();j++)
                                            {
                                                int k;
                                                for(k=0;k<mDate.size();k++) if(j==a[k]) {k=-1;break;}
                                                if(k==-1) continue;
                                                if (mDate.get(j).getP() >= latter && mDate.get(j).getP() < temp)
                                                {
                                                    temp = mDate.get(j).getP();
                                                    t = j;
                                                }
                                            }
                                            if(t!=-1) a[i]=t;
                                        }
                                        //依次显示

                                        rl1.post(new Runnable() {
                                            // Get size of view after layout
                                            @Override
                                            public void run() {
                                                h=rl1.getHeight();
                                                pheight=h/8-1;
                                            }
                                        });

                                        //逐个添加事件
                                        thingsToDo thing;
                                        for(int i=0;i<mDate.size();i++) {
                                            //Toast.makeText(FragmentTwo.super.getActivity(), "height:" + String.valueOf(rl1.getMeasuredHeight()), Toast.LENGTH_SHORT).show();
                                            thing=mDate.get(a[i]);
                                            int rl=-1,h=-1,l=-1;
                                            boolean flag=TRUE;
                                            //一类事件，有固定开始时间
                                            if(thing.getP()==0){
                                                rl=thing.get_line_Date(1);
                                                //判断能否添加
                                                for(int j=0;j<thing.getTit();j++){
                                                    if(thing.get_line_Time(1)-1+j>=32||ThingTable[rl-1][thing.get_line_Time(1)-1+j]!=0)
                                                    {
                                                        flag=FALSE;
                                                        break;
                                                    }
                                                }
                                                //添加事件,并修改时间表
                                                if(flag==TRUE) {
                                                    h = get_h(thing.getTit());
                                                    l = get_l(thing.get_line_Time(1));
                                                    addNewView(thing,rl, h, l);
                                                    for (int j = 0; j < thing.getTit(); j++) {
                                                        ThingTable[rl - 1][thing.get_line_Time(1) - 1 + j] = 1;
                                                    }
                                                }
                                                else{
                                                    Toast.makeText(FragmentTwo.super.getActivity(), "事件" + thing.getTitle()+"添加失败", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            //二类事件，无固定时间要求
                                            else if(thing.getP()==999){
                                                //判断能否添加
                                                flag=FALSE;
                                                int t=0,count=0;
                                                for(int day=Cweekday();day<=7;day++)
                                                {
                                                    t=0;count=0;
                                                    int y=1;
                                                    if(day==Cweekday()){
                                                        int hour=Chour(),minute=Cminute();
                                                        if(hour>=7) {
                                                            y = (hour - 7) * 2 + 2;
                                                            if (minute >= 30) y = y + 1;
                                                        }
                                                    }
                                                    for(int j=y;j<=32;j++)
                                                    {
                                                        if(ThingTable[day-1][j-1]!=0) {
                                                            t=0;count=0;
                                                            continue;
                                                        }
                                                        else{
                                                            t=j;count++;
                                                        }
                                                        if(count==thing.getTit()){
                                                            rl=day;
                                                            l=get_l(t-count+1);
                                                            flag=TRUE;
                                                            break;
                                                        }
                                                    }
                                                    if(flag==TRUE) break;
                                                }
                                                //添加事件,并修改时间表
                                                if(flag==TRUE){
                                                    h=get_h(thing.getTit());
                                                    addNewView(thing,rl,h,l);
                                                    for (int j = 0; j < thing.getTit(); j++) {
                                                        ThingTable[rl-1][t-count + j] = 1;
                                                    }
                                                }
                                            }
                                            //三类事件，无开始时间，有结束时间
                                            else{
                                                //判断能否添加
                                                flag=FALSE;
                                                int t=0,count=0;
                                                for(int day=Cweekday();day<=7;day++)
                                                {
                                                    t=0;count=0;
                                                    int y=1;
                                                    if(day==Cweekday()){
                                                        int hour=Chour(),minute=Cminute();
                                                        if(hour>=7) {
                                                            y = (hour - 7) * 2 + 2;
                                                            if (minute >= 30) y = y + 1;
                                                        }
                                                    }
                                                    for(int j=y;j<=32;j++)
                                                    {
                                                        if(day==thing.get_line_Date(2)&&j>=thing.get_line_Time(2)-1)
                                                        {
                                                            Toast.makeText(FragmentTwo.super.getActivity(), "事件" + thing.getTitle()+"添加失败", Toast.LENGTH_SHORT).show();
                                                            t=-1;
                                                            break;
                                                        }
                                                        if(ThingTable[day-1][j-1]!=0) {
                                                            t=0;count=0;
                                                            continue;
                                                        }
                                                        else{
                                                            t=j;count++;
                                                        }
                                                        if(count==thing.getTit()){
                                                            rl=day;
                                                            l=get_l(t-count+1);
                                                            flag=TRUE;
                                                            break;
                                                        }
                                                    }
                                                    if(flag==TRUE||t==-1) break;
                                                }
                                                //添加事件,并修改时间表
                                                if(flag==TRUE){
                                                    h=get_h(thing.getTit());
                                                    addNewView(thing,rl,h,l);
                                                    for (int j = 0; j < thing.getTit(); j++) {
                                                        ThingTable[rl-1][t-count + j] = 1;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
        );

        // Inflate the layout for this fragment
        return mView;
    }

    private int FtoINT(double x) //四舍五入转int
    {
        int xx;
        if(x-(int)x>=0.5) xx=(int)x+1;
        else xx=(int)x;
        return xx;
    }

    private int get_h(int t)
    {
        return FtoINT(t/4.0*pheight);
    }
    private int get_l(int t)
    {
        return FtoINT((t-1)/4.0*(pheight+1));
    }

    private int Cweekday() //当前周几
    {
        Calendar c=Calendar.getInstance();
        int weekdays=c.get(Calendar.DAY_OF_WEEK)-1;
        if(weekdays==0) weekdays=7;
        return weekdays;
    }
    private int Chour() //当前几时
    {
        Calendar c=Calendar.getInstance();
        int hour=c.get(Calendar.HOUR_OF_DAY);
        return hour;
    }
    private int Cminute() //当前几分
    {
        Calendar c=Calendar.getInstance();
        int minute=c.get(Calendar.MINUTE);
        return minute;
    }


}
