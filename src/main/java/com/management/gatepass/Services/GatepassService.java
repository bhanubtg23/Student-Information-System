package com.management.gatepass.Services;

import com.management.gatepass.Constants.GatepassTypeEnum;
import com.management.gatepass.Constants.RequestStatus;
import com.management.gatepass.Entity.AuthurizingPersonDetails;
import com.management.gatepass.Entity.Gatepass;
import com.management.gatepass.HttpBody.GatepassInfo;
import com.management.gatepass.Translators.GatepassTranslator;
import com.management.gatepass.Repository.mongo.GatepassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GatepassService {

    @Autowired
    GatepassRepository gatepassRepository;

    @Autowired
    UserService userService;

    public List<Gatepass> listAllDetails() {
        return gatepassRepository.findAll();
    }

    public Optional<Gatepass> findDetailsById(String id) {
        return gatepassRepository.findById(id);
    }

    public String fetchStatusById(String id) {
        Optional<Gatepass> gatepass = gatepassRepository.findById(id);
        return gatepass.map(value -> value.getStatus().getValue()).orElse(null);

    }

    public String saveFormDetails(GatepassInfo gatepassInfo) {
        GatepassTranslator gatepassTranslator = new GatepassTranslator();
        //TODO check for of user is present or not.
        Gatepass gatepass = gatepassTranslator.translate(gatepassInfo);
        gatepassRepository.save(gatepass);
        //TODO save in visiting user repository.
        return "Details Stored successfully";
    }

    public String setStatusAndAuthPersonInfo(String gatePassId, String status, AuthurizingPersonDetails authPersonInfo) {
        Optional<Gatepass> gatepassDetails = gatepassRepository.findById(gatePassId);
        if (gatepassDetails.isEmpty()){
            return null;
        }
        gatepassDetails.get().setStatus(RequestStatus.valueOf(status));
        gatepassDetails.get().setAuthurizingPersonDetails(authPersonInfo);
        gatepassRepository.save(gatepassDetails.get());
        return "Entry updated successfully";
    }

    public Map getAllRequestsDetails() {
        List<Gatepass> approvedRequest = gatepassRepository.findAllByStatusEqualsOrderByDateAsc(RequestStatus.APPROVED);
        List<Gatepass> rejectedRequest = gatepassRepository.findAllByStatusEqualsOrderByDateAsc(RequestStatus.REJECTED);
        List<Gatepass> pendingRequest = gatepassRepository.findAllByStatusEqualsOrderByDateAsc(RequestStatus.PENDING);
        List<Gatepass> inwardRequest = gatepassRepository.findAllByGatepassTypeEqualsOrderByDateAsc(GatepassTypeEnum.INWARD);
        List<Gatepass> outwardRequest = gatepassRepository.findAllByGatepassTypeEqualsOrderByDateAsc(GatepassTypeEnum.OUTWARD);

        Map<Integer, List<Gatepass>> allRequests = new HashMap<>();
        allRequests.put(approvedRequest.size(), approvedRequest);
        allRequests.put(rejectedRequest.size(), rejectedRequest);
        allRequests.put(pendingRequest.size(), pendingRequest);
        allRequests.put(inwardRequest.size(), inwardRequest);
        allRequests.put(outwardRequest.size(), outwardRequest);
        return allRequests;
    }
}
