package gov.samhsa.mhc.trypolicy.service;


import gov.samhsa.mhc.common.document.converter.DocumentXmlConverter;
import gov.samhsa.mhc.common.document.transformer.XmlTransformer;
import gov.samhsa.mhc.common.param.Params;
import gov.samhsa.mhc.trypolicy.config.DSSProperties;
import gov.samhsa.mhc.trypolicy.exception.TryPolicyException;
import gov.samhsa.mhc.trypolicy.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;

import javax.xml.transform.URIResolver;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TryPolicyServiceImpl implements TryPolicyService {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DSSProperties dssProperties;

    @Autowired
    DocumentXmlConverter documentXmlConverter;

    @Autowired
    private XmlTransformer xmlTransformer;

    @Override
    public String getSegmentDocXHTML(String patientUserName, String patientId, String documentId, String consentId, String purposeOfUseCode) throws TryPolicyException {

        return getTaggedC32(getSegmentDocXML(patientUserName, patientId, documentId, consentId, purposeOfUseCode));

    }



    @Override
    public String getSegmentDocXML(String patientUserName, String patientId, String documentId, String consentId, String purposeOfUseCode) throws TryPolicyException {


        RestTemplate restTemplate = new RestTemplate();
        CCDDto ccdStrDto = restTemplate.getForObject(dssProperties.getCcdUrl() + patientUserName + "/" + documentId, CCDDto.class);
        String docStr = new String(ccdStrDto.getCCDFile());

        XacmlDto xacmlDto = restTemplate.getForObject(dssProperties.getXacmlUrl() + consentId, XacmlDto.class);
        String xacmlStr = new String(xacmlDto.getXacmlFile());

        return invokeDssService(patientId, docStr, xacmlStr, purposeOfUseCode);
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
