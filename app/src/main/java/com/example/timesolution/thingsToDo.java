package com.example.timesolution;


import java.util.Calendar;
import java.util.Random;

public class thingsToDo {
    private DateLine
            startline,
            deadline;
    private String tit;          //time it takes 需要的时间
    private int impt,         //importance 重要程度
            enmg,         //emergency 紧急程度
            p;            //总值
    private String title, desc;//要显示的事件标题和内容
    private int ranColor;

    public thingsToDo() {
        startline = new DateLine();
        deadline = new DateLine();

        title = "测试一";
        desc = "这是一个测试事件";

        startline.date = "无";
        startline.time = "无";
        deadline.date = "星期六";
        deadline.time = "23:00";
        tit = "100";
        impt = 80;
        enmg = 999;
        ranColor = (int) (Math.random() * 5);
    }

    public thingsToDo(String title, String desc, DateLine stl, DateLine ddl, String impt, String tit) {
        this.title = title;
        this.desc = desc;

        this.startline = stl;
        this.deadline = ddl;
        this.tit = tit;
        this.impt = Integer.parseInt(impt);
        this.enmg = 999;
        this.p = setp();
        ranColor = (int) (new Random(System.currentTimeMillis()).nextInt(5));
    }

    public int get_line_Date(int i) {
        String x;
        if (i == 1) {
            x = startline.date;
        } else if (i == 2) {
            x = deadline.date;
        } else return -1;

        if (x.equals("无")) return 0;
        else if (x.equals("周一")) return 1;
        else if (x.equals("周二")) return 2;
        else if (x.equals("周三")) return 3;
        else if (x.equals("周四")) return 4;
        else if (x.equals("周五")) return 5;
        else if (x.equals("周六")) return 6;
        else if (x.equals("周日")) return 7;
        else return -1;
    }

    public int get_line_Time(int i) {
        String x;
        if (i == 1) {
            x = startline.time;
        } else if (i == 2) {
            x = deadline.time;
        } else return -1;

        if (x.equals("无")) return 0;
        else if (x.equals("7:00")) return 1;
        else if (x.equals("7:30")) return 2;
        else if (x.equals("8:00")) return 3;
        else if (x.equals("8:30")) return 4;
        else if (x.equals("9:00")) return 5;
        else if (x.equals("9:30")) return 6;
        else if (x.equals("10:00")) return 7;
        else if (x.equals("10:30")) return 8;
        else if (x.equals("11:00")) return 9;
        else if (x.equals("11:30")) return 10;
        else if (x.equals("12:00")) return 11;
        else if (x.equals("12:30")) return 12;
        else if (x.equals("13:00")) return 13;
        else if (x.equals("13:30")) return 14;
        else if (x.equals("14:00")) return 15;
        else if (x.equals("14:30")) return 16;
        else if (x.equals("15:00")) return 17;
        else if (x.equals("15:30")) return 18;
        else if (x.equals("16:00")) return 19;
        else if (x.equals("16:30")) return 20;
        else if (x.equals("17:00")) return 21;
        else if (x.equals("17:30")) return 22;
        else if (x.equals("18:00")) return 23;
        else if (x.equals("18:30")) return 24;
        else if (x.equals("19:00")) return 25;
        else if (x.equals("19:30")) return 26;
        else if (x.equals("20:00")) return 27;
        else if (x.equals("20:30")) return 28;
        else if (x.equals("21:00")) return 29;
        else if (x.equals("21:30")) return 30;
        else if (x.equals("22:00")) return 31;
        else if (x.equals("22:30")) return 32;
        else if (x.equals("23:00")) return 33;
        else return -1;
    }

    public int setp() {
        double p = 0;
        if (this.startline.date.equals("无") && this.deadline.date.equals("无")) {
            this.enmg = 999;
            p = 999;
        } else if (!this.startline.date.equals("无") && this.deadline.date.equals("无")) {
            this.enmg = 0;
            p = 0;
        } else if (this.startline.date.equals("无") && !this.deadline.date.equals("无")) {
            Calendar c = Calendar.getInstance();
            int weekdays = c.get(Calendar.DAY_OF_WEEK) - 1;
            if (weekdays == 0) weekdays = 7;
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            int lasttime = (get_line_Date(2) - weekdays) * 24 * 60 + (get_line_Time(2) - 1) * 30 + 7 * 60 - hour * 60 - minute;
            this.enmg = lasttime / 10;
            p = Math.sqrt(this.enmg * this.enmg + this.impt * this.impt);
        }
        this.p = (int) p;
        return this.p;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getstl() {
        return this.startline.date + this.startline.time;
    }

    public String getddl() {
        return this.deadline.date + this.deadline.time;
    }

    public int getImpt() {
        return this.impt;
    }

    public int getEnmg() {
        return this.enmg;
    }

    public int getP() {
        return p;
    }

    public int getTit() {
        return Integer.parseInt(this.tit);
    }

    public int getRanColor() {
        return ranColor;
    }
}
