package com.karam.rest.rest_messages.requests;

public class Contractor {
    private String fname;
    private String location;
    private String phone_no;
    private String username;
    private String password;

    public Contractor(String name, String location, String PhoneNo, String username, String password) {
        this.fname = name;
        this.location = location;
        this.phone_no = PhoneNo;
        this.username = username;
        this.password = password;
    }
}
