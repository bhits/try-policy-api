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
    public String getSegmentDocXHTML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws TryPolicyException {

        String tryPolicy = "trypolicy segment";

        tryPolicy = dssService.getSegmentDocXHTML(ccdXml, xacmlPolicy, purposeOfUse);

        return tryPolicy;
    }

    @Override
    public String getRedactedDocXHTML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws TryPolicyException {
        String segmentedC32 = "trypolicy segment";

        segmentedC32 = dssService.getRedactedDocXHTML(ccdXml, xacmlPolicy, purposeOfUse);

        return segmentedC32;
    }



    @Override
    public String getSegmentDocXML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws TryPolicyException {
        String tryPolicy = "trypolicy segment";

        tryPolicy = dssService.getSegmentDocXML(ccdXml, xacmlPolicy, purposeOfUse);

        return tryPolicy;
    }

    @Override
    public String getRedactedDocXML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws TryPolicyException {
        String segmentedC32 = "trypolicy segment";

        segmentedC32 = dssService.getRedactedDocXML(ccdXml, xacmlPolicy, purposeOfUse);

        return segmentedC32;
    }
}
