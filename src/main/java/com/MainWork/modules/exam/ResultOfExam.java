package com.MainWork.modules.exam;


public class ResultOfExam {
    private String name;
    private String measure;
    private double result;
    private double two;
    private double three;
    private double four;
    private double five;

    public ResultOfExam(String name, String measure, double result, double two, double three, double four, double five) {
        this.name = name;
        this.measure = measure;
        this.result = result;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getTwo() {
        return two;
    }

    public void setTwo(double two) {
        this.two = two;
    }

    public double getThree() {
        return three;
    }

    public void setThree(double three) {
        this.three = three;
    }

    public double getFour() {
        return four;
    }

    public void setFour(double four) {
        this.four = four;
    }

    public double getFive() {
        return five;
    }

    public void setFive(double five) {
        this.five = five;
    }
}
