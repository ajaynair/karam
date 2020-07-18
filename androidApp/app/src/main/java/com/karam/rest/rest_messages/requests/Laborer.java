package com.karam.rest.rest_messages.requests;

import androidx.annotation.NonNull;

public class Laborer {
    private int id;
    private int parentId;
    private String first_name;
    private String last_name;
    private int age;
    private String gender;
    private String address;
    private String skills;
    private String phone_number;
    private String aadhar_card_status;
    private String aadharNumber;
    private String password;
    private final String username;
    private String panCard;
    private String active_ind;
    private String preferred_job_location;

    public Laborer(int parentId, String fname, String lname, String location, String PhoneNo, int age, String gender, String aadharStatus, String skills, String username, String password) {
        this.id = 1;
        this.parentId = parentId;
        this.first_name = fname;
        this.last_name = lname;
        this.address = location;
        this.phone_number = PhoneNo;
        this.age = age;
        this.gender = gender;
        this.aadhar_card_status = aadharStatus;
        this.panCard = "TestPanCardNo";
        this.skills = skills;
        this.active_ind = "Active";
        this.preferred_job_location = location;
        this.username = username;
        this.password = password;
        this.aadharNumber = "testAadharNo";
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getAadhar_card_status() {
        return aadhar_card_status;
    }

    public void setAadhar_card_status(String aadhar_card_status) {
        this.aadhar_card_status = aadhar_card_status;
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

    public String getActive_ind() {
        return active_ind;
    }

    public void setActive_ind(String active_ind) {
        this.active_ind = active_ind;
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
