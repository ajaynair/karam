package com.karam.rest.rest_messages.requests;

import androidx.annotation.NonNull;

public class LaborerActiveStatus {
    private String active_ind;

    public LaborerActiveStatus(String activeInd) {
        this.active_ind = activeInd;
    }

    public String getActive_ind() {
        return active_ind;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(this.active_ind);
    }
}
