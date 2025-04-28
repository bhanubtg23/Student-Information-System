package com.management.gatepass.Controller;

import com.management.gatepass.Entity.AuthurizingPersonDetails;
import com.management.gatepass.Entity.Gatepass;
import com.management.gatepass.HttpBody.GatepassInfo;
import com.management.gatepass.Services.GatepassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//TODO - suggestion on api names
//TODO to decide, will need more api or not.

@RestController()
@RequestMapping("/gatepass/v1/")
public class GatepassController {

    @Autowired
    private GatepassService gatepassService;

    /*ADMIN ACTIONS*/
    @GetMapping(value = "admin/getRequestsDetails")
    public ResponseEntity<Map> getRequestsDetails() {
        Map<Object, Object> requestsDetails = gatepassService.getAllRequestsDetails();
        if(requestsDetails.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(requestsDetails, HttpStatus.OK);
    }

    @GetMapping(value = "admin/getGatepassLists")
    public ResponseEntity<List> getGatepassLists() {
        List<Gatepass> requestsDetails = gatepassService.listAllDetails();
        if(requestsDetails.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(requestsDetails, HttpStatus.OK);
    }

    /*USER ACTIONS*/
    @GetMapping(value ="user/{id}")
    @ResponseBody
    public ResponseEntity<Gatepass> fetchDetailsById(@PathVariable String id) {
        Optional<Gatepass> gatepassDetails = gatepassService.findDetailsById(id);
        if (gatepassDetails.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gatepassDetails.get(), HttpStatus.OK);
    }

    @PostMapping(value = "user/saveFormDetails")
    @ResponseBody
    public ResponseEntity<String> saveFormDetails(@RequestBody GatepassInfo gatepass){
        String status = gatepassService.saveFormDetails(gatepass);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping(value ="user/{id}/")
    @ResponseBody
    public ResponseEntity<String> fetchStatusById(@PathVariable String id){
        String status = gatepassService.fetchStatusById(id);
        if (status == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping(value = "user/setStatus/{status}/gatePassId/{gatePassId}")
    public ResponseEntity<String> setStatus(@PathVariable String status,
                                            @PathVariable String gatePassId,
                                            @RequestBody AuthurizingPersonDetails authPersonInfo){
        String responseStatus = gatepassService.setStatusAndAuthPersonInfo(gatePassId, status, authPersonInfo);
        if (responseStatus == null){
            return new ResponseEntity<>("Entry "+gatePassId+ "not found.", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseStatus, HttpStatus.OK);
    }
}
