package com.karam.rest.rest_messages.requests;

public class Contractor {
    private final String fname;
    private final String location;
    private final String phone_no;
    private final String username;
    private final String password;

    public Contractor(String name, String location, String PhoneNo, String username, String password) {
        this.fname = name;
        this.location = location;
        this.phone_no = PhoneNo;
        this.username = username;
        this.password = password;
    }
}
