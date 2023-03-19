package com.example.teacherszone;

public class attendenceviewmodel {

    String name,roll;

    public attendenceviewmodel() {
    }

    public attendenceviewmodel(String name, String roll) {
        this.name = name;
        this.roll = roll;
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
}

