package com.MainWork.modules.schedule;

public class Schedule {
    private int id;
    private int coachid;
    private int dayid;
    private int timeid;

    public Schedule(int id, int coachid, int dayid, int timeid) {
        this.id = id;
        this.coachid = coachid;
        this.dayid = dayid;
        this.timeid = timeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoachid() {
        return coachid;
    }

    public void setCoachid(int coachid) {
        this.coachid = coachid;
    }

    public int getDayid() {
        return dayid;
    }

    public void setDayid(int dayid) {
        this.dayid = dayid;
    }

    public int getTimeid() {
        return timeid;
    }

    public void setTimeid(int timeid) {
        this.timeid = timeid;
    }
}
