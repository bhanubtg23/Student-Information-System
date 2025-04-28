package com.management.gatepass.Constants;

public enum GatepassTypeEnum {
    INWARD("Gatepass type is inward"),
    OUTWARD("Gatepass type is outward");

    private String gatepassType;
    GatepassTypeEnum(String s) {
        gatepassType = s;
    }
}
