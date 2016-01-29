package gov.samhsa.tryPolicy.service;

import gov.samhsa.dss.service.DSSService;
import gov.samhsa.tryPolicy.aspects.Loggable;
import gov.samhsa.tryPolicy.exception.TryPolicyException;
import gov.samhsa.tryPolicy.service.dto.CCDDto;
import gov.samhsa.tryPolicy.service.dto.XacmlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
public class TryPolicyServiceImpl implements TryPolicyService {

    @Value("${pcm.ccd.url}")
    private String ccdUrl;

    @Value("${pcm.xacml.url}")
    private String xacmlUrl ;

    @Autowired
    DSSService dssService;


    @Override
    @Loggable
    public String getSegmentDocXHTML(String patientUserName, String documentId, String consentId, String purposeOfUseCode) throws TryPolicyException {

        String tryPolicy = "trypolicy segment";

        RestTemplate restTemplate = new RestTemplate();
        CCDDto ccdStrDto = restTemplate.getForObject(ccdUrl +patientUserName+"/" + documentId, CCDDto.class);
        String docStr = new String(ccdStrDto.getCCDFile());

        XacmlDto xacmlDto = restTemplate.getForObject(xacmlUrl + consentId, XacmlDto.class);
        String xacmlStr = new String(xacmlDto.getXacmlFile());

        tryPolicy = dssService.getSegmentDocXHTML(docStr, xacmlStr , purposeOfUseCode);
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
