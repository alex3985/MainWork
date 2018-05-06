package com.MainWork.modules.users;

public class Student {
    private int Id;
    private String Name;
    private String Surname;
    private String Patronymic;
    private String Group;
    private String Sex;
    private String Phone;
    private int Id_Faculty;

    public Student(int id, String name, String surname, String patronymic, String group, String sex, int id_Faculty) {
        Id = id;
        Name = name;
        Surname = surname;
        Patronymic = patronymic;
        Group = group;
        Sex = sex;
        Id_Faculty = id_Faculty;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public void setPatronymic(String patronymic) {
        Patronymic = patronymic;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getId_Faculty() {
        return Id_Faculty;
    }

    public void setId_Faculty(int id_Faculty) {
        Id_Faculty = id_Faculty;
    }
}
