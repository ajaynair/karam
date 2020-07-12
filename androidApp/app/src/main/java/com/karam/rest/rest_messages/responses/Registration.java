package com.karam.rest.rest_messages.responses;

public class Registration {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;

    //Setters and getters

    public String toString() {
        return "UserApiResponse [data=" + userId + "]";
    }
}

