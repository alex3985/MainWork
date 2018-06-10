package com.MainWork.modules.schedule;

public class Schedule {
    private int id;
    private int dayid;
    private String day;
    private int timeid;
    private String time;

    public Schedule(int id, int dayid, String day, int timeid, String time) {
        this.id = id;
        this.dayid = dayid;
        this.day = day;
        this.timeid = timeid;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDayid() {
        return dayid;
    }

    public void setDayid(int dayid) {
        this.dayid = dayid;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTimeid() {
        return timeid;
    }

    public void setTimeid(int timeid) {
        this.timeid = timeid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
