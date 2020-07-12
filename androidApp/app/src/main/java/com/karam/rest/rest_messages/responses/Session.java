package com.karam.rest.rest_messages.responses;

public class Session {
    private int session_id;
    private String role_type;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private int user_id;

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
