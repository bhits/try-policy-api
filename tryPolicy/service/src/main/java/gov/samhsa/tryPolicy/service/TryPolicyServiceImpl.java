package gov.samhsa.tryPolicy.service;

import gov.samhsa.dss.service.DSSService;
import gov.samhsa.tryPolicy.aspects.Loggable;
import gov.samhsa.tryPolicy.exception.TryPolicyException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
public class TryPolicyServiceImpl implements TryPolicyService {

    @Autowired
    DSSService dssService;


    @Override
    @Loggable
    public String tryPolicy(String ccdXml, String xacmlPolicy, String purposeOfUse) throws TryPolicyException {

        String segmentedC32 = "trypolicy segment";

        segmentedC32 = dssService.getSegmentedDoc(ccdXml, xacmlPolicy, purposeOfUse);

        return segmentedC32;
    }


}
