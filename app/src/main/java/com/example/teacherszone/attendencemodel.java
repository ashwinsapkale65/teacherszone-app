package com.example.teacherszone;

public class attendencemodel {

    String name,roll,CLASS,DIV,ROLL;

    public attendencemodel() {
    }

    public attendencemodel(String name, String roll, String CLASS, String DIV) {
        this.name = name;
        this.roll = roll;
        this.CLASS = CLASS;
        this.DIV = DIV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getCLASS() {
        return CLASS;
    }

    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public String getDIV() {
        return DIV;
    }

    public void setDIV(String DIV) {
        this.DIV = DIV;
    }
}
