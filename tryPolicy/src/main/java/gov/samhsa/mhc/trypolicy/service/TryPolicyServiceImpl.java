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
    public String getSegmentDocXHTML(String patientUsername, String patientId, String documentId, String consentId, String purposeOfUseCode) throws TryPolicyException {
        return getTaggedC32(getSegmentDocXML(patientUsername, patientId, documentId, consentId, purposeOfUseCode));
    }

    @Override
    public String getSegmentDocXML(String patientUsername, String patientId, String documentId, String consentId, String purposeOfUseCode) throws TryPolicyException {
        CCDDto ccdStrDto = pcmService.getCCDByPatientUsernameAndDocumentId(patientUsername, documentId);
        String docStr = new String(ccdStrDto.getCCDFile());
        List<String> obligations = pcmService.getObligationsByPatientUsernameAndConsentId(patientUsername, consentId);
        return invokeDssService(patientId, docStr, obligations, purposeOfUseCode);
    }

    private String getTaggedC32(String segmentedC32) {
        final Document taggedC32Doc = documentXmlConverter
                .loadDocument(segmentedC32);
        changeXslPath(taggedC32Doc);
        final NodeList taggedC32List = taggedC32Doc
                .getElementsByTagName("entry");

        final Document segmentedC32Doc = documentXmlConverter
                .loadDocument(segmentedC32);
        final NodeList segmentedC32List = segmentedC32Doc
                .getElementsByTagName("entry");

        //  logger.info("Original C32: " + originalC32);
        logger.info("Segmented C32: " + segmentedC32);

        logger.info("Tagged C32 Entry size: " + taggedC32List.getLength());
        logger.info("Segmented C32 Entry size: "
                + segmentedC32List.getLength());

        // xslt transformation
        final String xslUrl = Thread.currentThread()
                .getContextClassLoader().getResource("CDA_flag_redact.xsl")
                .toString();

        final String output = xmlTransformer.transform(taggedC32Doc, xslUrl, Optional.<Params>empty(), Optional.<URIResolver>empty());

        logger.info("Printing transformed xslt: " + output);
        return output;
    }

    private String invokeDssService(String patientId, String ccdStr, List<String> obligations, String purposeOfUse) {
        try {
            DSSRequest dssRequest = createDSSRequest(patientId, ccdStr, obligations, purposeOfUse);
            DSSResponse response = dssService.segmentDocument(dssRequest);
            return new String(response.getTryPolicyDocument(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new TryPolicyException(e.getMessage(), e);
        }
    }

    private DSSRequest createDSSRequest(String patientId, String ccdStr, List<String> obligations, String purposeOfUse) {
        DSSRequest dssRequest = new DSSRequest();
        dssRequest.setAudited(new Boolean(dssProperties.getDefaultIsAudited()));
        dssRequest.setAuditFailureByPass(new Boolean(dssProperties.getDefaultIsAuditFailureByPass()));
        dssRequest.setDocument(ccdStr.getBytes(StandardCharsets.UTF_8));
        dssRequest.setEnableTryPolicyResponse(true);
        dssRequest.setDocumentEncoding("UTF-8");

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
     * @param taggedC32Doc the tagged c32 doc
     */
    private void changeXslPath(Document taggedC32Doc) {

        final String expression = "/processing-instruction('xml-stylesheet')";
        Optional<ProcessingInstruction> pi = Optional.empty();
        /**
         * Need to add xsl
         */
/*
        try {
            pi = documentAccessor.getProcessingInstruction(taggedC32Doc,
                    expression);
        } catch (final DocumentAccessorException e) {
            throw new DSSException("Error processing xsl path");
        }
*/

        // <?xml-stylesheet href="http://obhita.org/CDA.xsl" type="text/xsl"?>
        if (pi.isPresent()) {
            pi.get().setData("type='text/xsl' href='CDA_flag_redact.xsl'");
        } else {
            // Add xml style sheet at the second line of xml string
            final ProcessingInstruction p = taggedC32Doc
                    .createProcessingInstruction("xml-stylesheet",
                            "type=\"text/xsl\" href=\"CDA_flag_redact.xsl\"");
            final Element stylesheetEl = taggedC32Doc.getDocumentElement();
            taggedC32Doc.insertBefore(p, stylesheetEl);
        }
    }
}
