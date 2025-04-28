package com.management.gatepass.HttpBody;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class GatepassInfo {
    private String id;
    private String userId; //get user details by this
    private String email;
    //private LocalDate date;
    private String location;
    private String gatepassType;
    /*private LocalDate entryTime; //get this by local timer and date.
    private LocalDate exitTime;*/
    private String description;
    private String passNumber;
    private String contents;
    private String vehicleNumber;
    private String purposeOfVisit;
    private String status;
    private String watchManName;
}
