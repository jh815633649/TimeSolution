package com.example.timesolution;

import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FragmentThree extends Fragment {
    private View mView;
    private ImageButton button_tomato;
    private TextView timer_tomato,Reminder;
    private ProgressBar Progressbar_tomato;
    private DownCount timer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_fragment_three, container, false);

        button_tomato=mView.findViewById(R.id.button_tomato);
        timer_tomato=mView.findViewById(R.id.timer_tomato);
        Reminder=mView.findViewById(R.id.Reminder);
        Progressbar_tomato=mView.findViewById(R.id.progressBar);
        timer_tomato.setText("25:00");
        timer=new DownCount(25*60*1000,1000) {
            @Override
            //每秒调用一次
            public void onTick(long millisUntilFinished) {
                timer_tomato.setText(formatTime(millisUntilFinished));
                Progressbar_tomato.incrementProgressBy(1);
            }

            @Override
            //结束时执行一次
            public void onFinish() {
                //
            }

            //时间格式转换
            public String formatTime(long millisecond) {
                int minute;//分钟
                int second;//秒数
                minute = (int) ((millisecond / 1000) / 60);
                second = (int) ((millisecond / 1000) % 60);
                if (minute < 10) {
                    if (second < 10) {
                        return "0" + minute + ":" + "0" + second;
                    } else {
                        return "0" + minute + ":" + second;
                    }
                }else {
                    if (second < 10) {
                        return minute + ":" + "0" + second;
                    } else {
                        return minute + ":" + second;
                    }
                }
            }

        };

        button_tomato.setOnClickListener(new View.OnClickListener()
                                         {
                                             @Override
                                             public void onClick(View v)
                                             {
                                                 //todo
                                                 if(timer_tomato.getText().equals("25:00")) {
                                                     timer.start();
                                                     Reminder.setText("放下手机，去完成你的计划吧！");
                                                 }
                                                 else{
                                                     timer.cancel();
                                                     timer.reset();
                                                     timer_tomato.setText("25:00");
                                                     Reminder.setText("↑ 点击番茄开始计时 ↑");
                                                     Progressbar_tomato.setProgress(0);
                                                 }
                                             }
                                         }
        );
        return mView;
    }
}
