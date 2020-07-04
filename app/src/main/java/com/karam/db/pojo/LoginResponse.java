package com.karam.db.pojo;

public class LoginResponse {
    private int session_id;
    private String role_type;

    public LoginResponse(int session_id, String role_type) {
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
