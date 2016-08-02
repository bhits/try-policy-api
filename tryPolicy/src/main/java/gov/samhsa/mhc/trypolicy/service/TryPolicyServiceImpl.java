package gov.samhsa.mhc.trypolicy.service;


import gov.samhsa.mhc.common.document.converter.DocumentXmlConverter;
import gov.samhsa.mhc.common.document.transformer.XmlTransformer;
import gov.samhsa.mhc.common.param.Params;
import gov.samhsa.mhc.trypolicy.config.DSSProperties;
import gov.samhsa.mhc.trypolicy.infrastructure.DssService;
import gov.samhsa.mhc.trypolicy.infrastructure.PcmService;
import gov.samhsa.mhc.trypolicy.service.dto.*;
import gov.samhsa.mhc.trypolicy.service.exception.TryPolicyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;

import javax.xml.transform.URIResolver;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TryPolicyServiceImpl implements TryPolicyService {
    private static final String CCDA_STYLESHEET = "CCDA_flag_redact.xsl";
    private static final String C32_STYLESHEET = "CDA_flag_redact.xsl";
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

    @Override
    public TryPolicyResponse getSegmentDocXHTML(String patientUsername, String patientId, String documentId, String consentId, String purposeOfUseCode) {
        try {
            CCDDto ccdStrDto = pcmService.getCCDByPatientUsernameAndDocumentId(patientUsername, documentId);
            String docStr = new String(ccdStrDto.getCCDFile());
            List<String> obligations = pcmService.getObligationsByPatientUsernameAndConsentId(patientUsername, consentId);
            DSSRequest dssRequest = createDSSRequest(patientId, docStr, obligations, purposeOfUseCode);
            DSSResponse response = dssService.segmentDocument(dssRequest);
            return getTaggedClinicalDocument(response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new TryPolicyException(e.getMessage(), e);
        }
    }

    private TryPolicyResponse getTaggedClinicalDocument(DSSResponse response) {
        String segmentedClinicalDocument = new String(response.getTryPolicyDocument(), StandardCharsets.UTF_8);
        boolean isCCDADocument = response.isCCDADocument();
        String documentStyleSheet = selectStyleSheet(isCCDADocument);

        final Document taggedClinicalDocument = documentXmlConverter
                .loadDocument(segmentedClinicalDocument);
        changeXslPath(taggedClinicalDocument, documentStyleSheet);
        final NodeList taggedClinicalDocumentList = taggedClinicalDocument
                .getElementsByTagName("entry");

        final Document segmentedClinicalDocumentDoc = documentXmlConverter
                .loadDocument(segmentedClinicalDocument);
        final NodeList segmentedClinicalDocumentList = segmentedClinicalDocumentDoc
                .getElementsByTagName("entry");

        logger.info("Segmented Clinical Document: " + segmentedClinicalDocument);

        logger.info("Tagged Clinical Document Entry size: " + taggedClinicalDocumentList.getLength());
        logger.info("Segmented Clinical Document Entry size: "
                + segmentedClinicalDocumentList.getLength());

        logger.info("Is Segmented CCDA document: " + isCCDADocument);

        // xslt transformation
        final String xslUrl = Thread.currentThread()
                .getContextClassLoader().getResource(documentStyleSheet)
                .toString();
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

    /**
     * Changes xsl path to local xsl.
     *
     * @param taggedClinicalDocument the tagged clinical document doc
     */
    private void changeXslPath(Document taggedClinicalDocument, String styleSheet) {

        final String expression = "/processing-instruction('xml-stylesheet')";
        Optional<ProcessingInstruction> pi = Optional.empty();
        /**
         * Need to add xsl
         */
        // <?xml-stylesheet href="http://obhita.org/CDA.xsl" type="text/xsl"?>
        if (pi.isPresent()) {
            pi.get().setData("type='text/xsl' href='" + styleSheet + "'");
        } else {
            // Add xml style sheet at the second line of xml string
            final ProcessingInstruction p = taggedClinicalDocument
                    .createProcessingInstruction("xml-stylesheet",
                            "type=\"text/xsl\" href=\"" + styleSheet + "\"");
            final Element stylesheetEl = taggedClinicalDocument.getDocumentElement();
            taggedClinicalDocument.insertBefore(p, stylesheetEl);
        }
    }

    private String selectStyleSheet(boolean isCCDADocument) {
        if (isCCDADocument) {
            return CCDA_STYLESHEET;
        } else {
            return C32_STYLESHEET;
        }
    }
}
