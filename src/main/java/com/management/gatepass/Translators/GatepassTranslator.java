package com.management.gatepass.Translators;

import com.management.gatepass.Constants.GatepassContants;
import com.management.gatepass.Constants.GatepassTypeEnum;
import com.management.gatepass.Constants.RequestStatus;
import com.management.gatepass.Entity.Gatepass;
import com.management.gatepass.Entity.GatepassDetails;
import com.management.gatepass.HttpBody.GatepassInfo;

import java.time.LocalDate;
import java.time.LocalTime;

public class GatepassTranslator {

    public Gatepass translate(GatepassInfo gatepassInfo) {
        GatepassDetails gatepassDetails = GatepassDetails.builder()
                .entryTime(LocalTime.now())
                .exitTime(LocalTime.now())
                .description(gatepassInfo.getDescription())
                .passNumber(gatepassInfo.getPassNumber())
                .contents(gatepassInfo.getContents())
                .vehicleNumber(gatepassInfo.getVehicleNumber())
                .purposeOfVisit(gatepassInfo.getPurposeOfVisit())
                .build();

        return Gatepass.builder()
                .uid(gatepassInfo.getId())
                .userId(gatepassInfo.getUserId())
                .emailId(gatepassInfo.getEmail())
                .date(LocalDate.now())
                .location(GatepassContants.SOLAPUR_TERMINAL)
                .gatepassType(GatepassTypeEnum.valueOf(gatepassInfo.getGatepassType()))
                .gatepassDetails(gatepassDetails)
                .status(RequestStatus.valueOf(gatepassInfo.getStatus()))
                .watchManName(gatepassInfo.getWatchManName())
                .build();
    }
}
