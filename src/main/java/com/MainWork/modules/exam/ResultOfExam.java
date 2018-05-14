package com.MainWork.modules.exam;


public class ResultOfExam {
    private String name;
    private String measure;
    private double result;

    public ResultOfExam(String name, String measure, double result) {
        this.name = name;
        this.measure = measure;
        this.result = result;
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
}
