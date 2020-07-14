package com.karam.rest.rest_messages.requests;

import androidx.annotation.NonNull;

public class Laborer {
    private int id;
    private int parentId;
    private String first_name;
    private String lname;
    private int age;
    private String gender;
    private String address;
    private String skills;
    private String phone_number;
    private String aadharStatus;
    private String aadharNumber;
    private String password;

    private String panCard;
    private String activeInd;
    private String preferred_job_location;

    public Laborer(int parentId, String fname, String lname, String location, String PhoneNo, int age, String gender, String aadharStatus, String skills, String password) {
        this.id = 1;
        this.parentId = parentId;
        this.first_name = fname;
        this.lname = lname;
        this.address = location;
        this.phone_number = PhoneNo;
        this.age = age;
        this.gender = gender;
        this.aadharStatus = aadharStatus;
        this.panCard = "TestPanCardNo";
        this.skills = skills;
        this.activeInd = "Y";
        this.preferred_job_location = "Pune, Mumbai";
        this.password = password;
        this.aadharNumber = "testAadharNo";
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public String getPreferred_job_location() {
        return preferred_job_location;
    }

    public void setPreferred_job_location(String preferred_job_location) {
        this.preferred_job_location = preferred_job_location;
    }

    @NonNull
    @Override
    public String toString() {
        return id + this.first_name;
    }
}
