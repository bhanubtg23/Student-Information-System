package com.management.gatepass.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@ToString
@Setter @Getter
@Builder
public class GatepassDetails {
    private LocalTime entryTime;
    private LocalTime exitTime;
    private String description;
    private String passNumber;
    private String contents;
    private String vehicleNumber;
    private String purposeOfVisit;
}
