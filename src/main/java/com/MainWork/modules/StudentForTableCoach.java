package com.MainWork.modules;

public class StudentForTableCoach {
    private int id_student;
    private int id_faculty;
    private String initials;
    private String faculty;
    private String group;
    private int attendance;
    private String lastdate;
    private String phone;
    private String sex;

    public StudentForTableCoach(int id_student, int id_faculty, String initials, String faculty, String group, int attendance, String lastdate, String phone, String sex) {
        this.id_student = id_student;
        this.id_faculty = id_faculty;
        this.initials = initials;
        this.faculty = faculty;
        this.group = group;
        this.attendance = attendance;
        this.lastdate = lastdate;
        this.phone = phone;
        this.sex = sex;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_studnet) {
        this.id_student = id_studnet;
    }

    public int getId_faculty() {
        return id_faculty;
    }

    public void setId_faculty(int id_faculty) {
        this.id_faculty = id_faculty;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
