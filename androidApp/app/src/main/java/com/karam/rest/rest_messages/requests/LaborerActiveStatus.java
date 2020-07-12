package com.karam.rest.rest_messages.requests;

import androidx.annotation.NonNull;

public class LaborerActiveStatus {
    private Boolean activeInd;

    public LaborerActiveStatus(Boolean activeInd) {
        this.activeInd = activeInd;
    }

    public Boolean getActiveInd() {
        return activeInd;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(this.activeInd);
    }
}
