package com.MainWork.modules.admin;

public class CoachForAdmin {
    private int id;
    private int sectionid;
    private String surname;
    private String name;
    private String patronymic;
    private String section;
    private String login;
    private String password;

    public CoachForAdmin(int id, int sectionid, String surname, String name, String patronymic, String section, String login, String password) {
        this.id = id;
        this.sectionid = sectionid;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.section = section;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSectionid() {
        return sectionid;
    }

    public void setSectionid(int sectionid) {
        this.sectionid = sectionid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

