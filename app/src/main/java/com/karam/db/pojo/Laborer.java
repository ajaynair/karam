package com.karam.db.pojo;

import androidx.annotation.NonNull;

public class Laborer {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String location;
    private String skill;
    private String PhoneNo;
    private int aadharStatus;

    public Laborer(String name, String location, String PhoneNo, int age, String gender, int aadharStatus) {
        this.id = 1;
        this.name = name;
        this.location = location;
        this.PhoneNo = PhoneNo;
        this.age = age;
        this.gender = gender;
        this.aadharStatus = aadharStatus;
    }

    @NonNull
    @Override
    public String toString() {
        return id + this.name;
    }
}
