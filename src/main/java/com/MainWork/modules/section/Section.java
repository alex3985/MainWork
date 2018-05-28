package com.MainWork.modules.section;

public class Section {
    private int id;
    private String section;
    private String name;
    private String surname;
    private String patronomic;

    public Section(int id, String section) {
        this.id = id;
        this.section = section;
    }

    public Section(String section, String name, String surname, String patronomic) {
        this.section = section;
        this.name = name;
        this.surname = surname;
        this.patronomic = patronomic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronomic() {
        return patronomic;
    }

    public void setPatronomic(String patronomic) {
        this.patronomic = patronomic;
    }
}
