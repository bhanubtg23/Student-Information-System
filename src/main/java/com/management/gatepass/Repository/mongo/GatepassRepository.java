package com.management.gatepass.Repository.mongo;


import com.management.gatepass.Constants.GatepassTypeEnum;
import com.management.gatepass.Constants.RequestStatus;
import com.management.gatepass.Entity.Gatepass;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GatepassRepository extends MongoRepository<Gatepass, String> {
    public List<Gatepass> findAllByStatusEqualsOrderByDateAsc(RequestStatus requestStatus);
    public List<Gatepass> findAllByGatepassTypeEqualsOrderByDateAsc(GatepassTypeEnum gatepassType);

}
