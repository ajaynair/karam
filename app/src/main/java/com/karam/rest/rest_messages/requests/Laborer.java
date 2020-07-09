package com.karam.rest.rest_messages.requests;

import androidx.annotation.NonNull;

public class Laborer {
    private int id;
    private int parentId;
    private String fname;
    private String lname;
    private int age;
    private String gender;
    private String address;
    private String skills;
    private String phno;
    private String aadharStatus;
    private String aadharNumber;
    private String passwordHash;

    private String panCard;
    private String activeInd;
    private String preferred_location;

    public Laborer(int parentId, String fname, String lname, String location, String PhoneNo, int age, String gender, String aadharStatus, String skills) {
        this.id = 1;
        this.fname = fname;
        this.lname = lname;
        this.address = location;
        this.phno = PhoneNo;
        this.age = age;
        this.gender = gender;
        this.aadharStatus = aadharStatus;
        this.panCard = "TestPanCardNo";
        this.skills = skills;
        this.activeInd = "Y";
        this.preferred_location = "Pune, Mumbai";
        this.passwordHash = "test";
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getAadharStatus() {
        return aadharStatus;
    }

    public void setAadharStatus(String aadharStatus) {
        this.aadharStatus = aadharStatus;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getActiveInd() {
        return activeInd;
    }

    public void setActiveInd(String activeInd) {
        this.activeInd = activeInd;
    }

    public String getPreferred_location() {
        return preferred_location;
    }

    public void setPreferred_location(String preferred_location) {
        this.preferred_location = preferred_location;
    }

    @NonNull
    @Override
    public String toString() {
        return id + this.fname;
    }
}
