package gov.samhsa.mhc.trypolicy.service;

import gov.samhsa.mhc.trypolicy.config.DSSProperties;
import gov.samhsa.mhc.trypolicy.exception.TryPolicyException;
import gov.samhsa.mhc.trypolicy.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
public class TryPolicyServiceImpl implements TryPolicyService {

    @Autowired
    DSSProperties dssProperties;

    @Override
    public String getSegmentDocXHTML(String patientUserName, String patientId, String documentId, String consentId, String purposeOfUseCode) throws TryPolicyException {

        String tryPolicy = "trypolicy segment";

        RestTemplate restTemplate = new RestTemplate();
        CCDDto ccdStrDto = restTemplate.getForObject(dssProperties.getCcdUrl() + patientUserName + "/" + documentId, CCDDto.class);
        String docStr = new String(ccdStrDto.getCCDFile());

        XacmlDto xacmlDto = restTemplate.getForObject(dssProperties.getXacmlUrl() + consentId, XacmlDto.class);
        String xacmlStr = new String(xacmlDto.getXacmlFile());


        String segmentDocStr = invokeDssService(patientId, docStr, xacmlStr, purposeOfUseCode);

        return segmentDocStr;
    }


    private String invokeDssService(String patientId, String ccdStr, String xacmlStr, String purposeOfUse) {
        String segmentDocStr = "";

        DSSRequest dssRequest = createDSSRequest(patientId, ccdStr, xacmlStr, purposeOfUse);

        // REST api call
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders reqHeader = new HttpHeaders();
        List<MediaType> accepts = new ArrayList<MediaType>();
        accepts.add(MediaType.APPLICATION_JSON);
        reqHeader.setAccept(accepts);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try {
             HttpEntity<DSSRequest> reqEntity = new HttpEntity<DSSRequest>(dssRequest, reqHeader);
             ResponseEntity<DSSResponse> dssRepEntitiy = restTemplate.exchange(dssProperties.getDssUrl(), HttpMethod.POST, reqEntity, DSSResponse.class);
            if (dssRepEntitiy.getStatusCode().equals(HttpStatus.OK)) {
                segmentDocStr = new String(dssRepEntitiy.getBody().getTryPolicyDocument(), StandardCharsets.UTF_8);
            }
        } catch (Exception httpException) {
            httpException.getStackTrace();
        }
        return segmentDocStr;
    }


    private DSSRequest createDSSRequest(String patientId, String ccdStr, String xacmlStr, String purposeOfUse) {
        DSSRequest dssRequest = new DSSRequest();
        dssRequest.setAudited(new Boolean(dssProperties.getDefaultIsAudited()));
        dssRequest.setAuditFailureByPass(new Boolean(dssProperties.getDefaultIsAuditFailureByPass()));
        dssRequest.setDocument(ccdStr.getBytes(StandardCharsets.UTF_8));
        dssRequest.setEnableTryPolicyResponse(true);
        dssRequest.setDocumentEncoding("UTF-8");

        XacmlResult xacmlResult = new XacmlResult();
        xacmlResult.setHomeCommunityId(dssProperties.getHomeCommunityId());
        xacmlResult.setMessageId("1234");
        xacmlResult.setPdpDecision(dssProperties.getPdpDecision());
        xacmlResult.setSubjectPurposeOfUse(SubjectPurposeOfUse.fromAbbreviation(purposeOfUse));
        xacmlResult.setPatientId(patientId);
        List<String> obligations = Arrays.asList("HIV", "PSY", "ETH");
        xacmlResult.setPdpObligations(obligations);

        dssRequest.setXacmlResult(xacmlResult);

        return dssRequest;
    }

}
