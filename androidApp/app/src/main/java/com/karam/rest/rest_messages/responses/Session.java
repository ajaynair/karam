package com.karam.rest.rest_messages.responses;

public class Session {
    private int session_id;
    private String role_type;

    public Session(int session_id, String role_type) {
        this.session_id = session_id;
        this.role_type = role_type;
    }

    public int getSession_id() {
        return session_id;
    }

    public String getRole_type() {
        return role_type;
    }
}