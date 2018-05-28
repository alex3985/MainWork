package com.MainWork.modules.admin;

public class StudentForAdmin {
    private int id;
    private String student;
    private String faculty;
    private String group;
    private String section;
    private String coach;
    private String login;
    private String password;

    public StudentForAdmin(int id, String student, String faculty, String group, String section, String coach, String login, String password) {
        this.id = id;
        this.student = student;
        this.faculty = faculty;
        this.group = group;
        this.section = section;
        this.coach = coach;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
