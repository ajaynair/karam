package com.karam.db.pojo;

public class Session {
    private String sessionid;
    private int error;

    public Session(String sessionid, int error) {
        this.sessionid = sessionid;
        this.error = error;
    }
}
