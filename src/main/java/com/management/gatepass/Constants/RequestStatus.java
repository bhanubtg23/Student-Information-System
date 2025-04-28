package com.management.gatepass.Constants;

import lombok.ToString;

@ToString
public enum  RequestStatus {
    PENDING("PENDING."),
    APPROVED("APPROVED."),
    REJECTED("REJECTED.");

    private String status;

    RequestStatus(String s) {
        this.status = s;
    }

    public String getValue(){
        return this.status;
    }
}
