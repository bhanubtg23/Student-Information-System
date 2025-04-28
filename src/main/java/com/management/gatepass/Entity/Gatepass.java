package com.management.gatepass.Entity;

import com.management.gatepass.Constants.GatepassTypeEnum;
import com.management.gatepass.Constants.RequestStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Builder
@ToString
@Setter @Getter
@Document(collection = "gatepass" )
public class Gatepass {

    @Id
    private String uid;
    private String userId;
    private String emailId;
    private LocalDate date;
    private String location;
    private GatepassTypeEnum gatepassType;
    private GatepassDetails gatepassDetails;
    private RequestStatus status;
    private AuthurizingPersonDetails authurizingPersonDetails;
    private String watchManName;
}

