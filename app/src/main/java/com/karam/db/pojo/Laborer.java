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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public int getAadharStatus() {
        return aadharStatus;
    }

    public void setAadharStatus(int aadharStatus) {
        this.aadharStatus = aadharStatus;
    }

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
