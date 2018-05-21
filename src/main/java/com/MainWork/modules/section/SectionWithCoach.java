package com.MainWork.modules.section;

public class SectionWithCoach {
    private String section;
    private String name;
    private String surname;
    private String patronomic;

    public SectionWithCoach(String section, String name, String surname, String patronomic) {
        this.section = section;
        this.name = name;
        this.surname = surname;
        this.patronomic = patronomic;
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
