package gov.samhsa.mhc.trypolicy.service;


import gov.samhsa.mhc.common.document.converter.DocumentXmlConverter;
import gov.samhsa.mhc.common.document.transformer.XmlTransformer;
import gov.samhsa.mhc.common.log.Logger;
import gov.samhsa.mhc.common.log.LoggerFactory;
import gov.samhsa.mhc.common.param.Params;
import gov.samhsa.mhc.trypolicy.config.DSSProperties;
import gov.samhsa.mhc.trypolicy.infrastructure.DssService;
import gov.samhsa.mhc.trypolicy.infrastructure.PcmService;
import gov.samhsa.mhc.trypolicy.infrastructure.PhrService;
import gov.samhsa.mhc.trypolicy.service.dto.*;
import gov.samhsa.mhc.trypolicy.service.exception.TryPolicyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.transform.URIResolver;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TryPolicyServiceImpl implements TryPolicyService {
    private static final String CDA_STYLESHEET = "CDA_flag_redact.xsl";

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DSSProperties dssProperties;

    @Autowired
    private DocumentXmlConverter documentXmlConverter;

    @Autowired
    private XmlTransformer xmlTransformer;

    @Autowired
    private PcmService pcmService;

    @Autowired
    private DssService dssService;

    @Autowired
    private PhrService phrService;

    @Override
    public TryPolicyResponse getSegmentDocXHTML(String patientUsername, String documentId, String consentId, String purposeOfUseCode) {
        try {
            CCDDto ccdStrDto = pcmService.getCCDByPatientUsernameAndDocumentId(patientUsername, documentId);
            String docStr = new String(ccdStrDto.getCCDFile());
            List<String> obligations = pcmService.getObligationsByPatientUsernameAndConsentId(patientUsername, consentId);

            String patientId = phrService.getPatient().getId().toString();
            DSSRequest dssRequest = createDSSRequest(patientId, docStr, obligations, purposeOfUseCode);
            DSSResponse response = dssService.segmentDocument(dssRequest);
            return getTaggedClinicalDocument(response);
        } catch (Exception e) {
            logger.info(() -> "Apply TryPolicy failed: " + e.getMessage());
            logger.debug(e::getMessage, e);
            throw new TryPolicyException();
        }
    }

    private TryPolicyResponse getTaggedClinicalDocument(DSSResponse dssResponse) {
        String segmentedClinicalDocument = new String(dssResponse.getTryPolicyDocument(), StandardCharsets.UTF_8);
        final Document taggedClinicalDocument = documentXmlConverter
                .loadDocument(segmentedClinicalDocument);
        final NodeList taggedClinicalDocumentList = taggedClinicalDocument
                .getElementsByTagName("entry");

        logger.debug("Segmented Clinical Document: " + segmentedClinicalDocument);
        logger.info("Tagged Clinical Document Entry size: " + taggedClinicalDocumentList.getLength());
        logger.info("Is Segmented CCDA document: " + dssResponse.isCCDADocument());

        // xslt transformation
        final String xslUrl = Thread.currentThread().getContextClassLoader().getResource(CDA_STYLESHEET).toString();
        final String output = xmlTransformer.transform(taggedClinicalDocument, xslUrl, Optional.<Params>empty(), Optional.<URIResolver>empty());

        TryPolicyResponse tryPolicyResponse = new TryPolicyResponse();
        tryPolicyResponse.setDocument(output.getBytes());
        return tryPolicyResponse;
    }

    private DSSRequest createDSSRequest(String patientId, String ccdStr, List<String> obligations, String purposeOfUse) {
        DSSRequest dssRequest = new DSSRequest();
        dssRequest.setAudited(Boolean.valueOf(dssProperties.getDefaultIsAudited()));
        dssRequest.setAuditFailureByPass(Boolean.valueOf(dssProperties.getDefaultIsAuditFailureByPass()));
        dssRequest.setDocument(ccdStr.getBytes(StandardCharsets.UTF_8));
        dssRequest.setEnableTryPolicyResponse(true);
        dssRequest.setDocumentEncoding(dssProperties.getDocumentEncoding());

        XacmlResult xacmlResult = new XacmlResult();
        xacmlResult.setHomeCommunityId(dssProperties.getHomeCommunityId());
        xacmlResult.setMessageId(UUID.randomUUID().toString());
        xacmlResult.setPdpDecision(dssProperties.getPdpDecision());
        xacmlResult.setSubjectPurposeOfUse(SubjectPurposeOfUse.fromAbbreviation(purposeOfUse));
        xacmlResult.setPatientId(patientId);
        xacmlResult.setPdpObligations(obligations);

        dssRequest.setXacmlResult(xacmlResult);

        return dssRequest;
    }
}