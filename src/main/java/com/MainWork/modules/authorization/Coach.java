package com.MainWork.modules.authorization;

public class Coach {
    private int Id;
    private String Name;
    private String Surname;
    private String Patronymic;

    public Coach(int id, String name, String surname, String patronymic) {
        Id = id;
        Name = name;
        Surname = surname;
        Patronymic = patronymic;
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
}
