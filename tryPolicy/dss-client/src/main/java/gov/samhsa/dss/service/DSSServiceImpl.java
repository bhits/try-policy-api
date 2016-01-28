package gov.samhsa.dss.service;

import gov.samhsa.acs.brms.domain.SubjectPurposeOfUse;
import gov.samhsa.acs.brms.domain.XacmlResult;
import gov.samhsa.acs.common.dto.PdpRequestResponse;
import gov.samhsa.acs.common.dto.XacmlRequest;
import gov.samhsa.acs.common.dto.XacmlResponse;
import gov.samhsa.acs.common.tool.DocumentAccessor;
import gov.samhsa.acs.common.tool.DocumentXmlConverter;
import gov.samhsa.acs.common.tool.SimpleMarshaller;
import gov.samhsa.acs.common.tool.XmlTransformer;
import gov.samhsa.acs.common.tool.exception.SimpleMarshallerException;
import gov.samhsa.acs.contexthandler.ContextHandler;
import gov.samhsa.acs.dss.ws.schema.DSSRequest;
import gov.samhsa.acs.dss.ws.schema.DSSResponse;
import gov.samhsa.dss.exception.DSSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Created by sadhana.chandra on 11/18/2015.
 */
@Service
public class DSSServiceImpl implements DSSService {

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Autowired
    DocumentXmlConverter documentXmlConverter;

    @Autowired
    private DocumentAccessor documentAccessor;

    @Autowired
    private XmlTransformer xmlTransformer;

    @Autowired
    private ContextHandler contextHandler;

    @Autowired
    private SimpleMarshaller marshaller;

    @Override
    public String getRedactedDocXHTML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws DSSException {

         String redactedDocXHTML = null;
        try {
            DSSResponse  dssResponse = segmentDocument(createDSSRequest(ccdXml, xacmlPolicy, purposeOfUse));
            redactedDocXHTML = getTaggedC32(dssResponse.getSegmentedDocumentXml());
            System.out.println(redactedDocXHTML);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (SimpleMarshallerException e) {
            logger.error(e.getMessage(), e);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
        }
        return redactedDocXHTML;
    }

    @Override
    public String getSegmentDocXHTML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws DSSException {
        String segmentDocXHTML = null;
        try {
            DSSResponse  dssResponse = segmentDocument(createDSSRequest(ccdXml, xacmlPolicy, purposeOfUse));
            segmentDocXHTML = getTaggedC32(dssResponse.getTryPolicyDocumentXml());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (SimpleMarshallerException e) {
            logger.error(e.getMessage(), e);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
        }
        return segmentDocXHTML;
    }

    @Override
    public String getRedactedDocXML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws DSSException {

        String redactedDocXML = null;
        try {
            DSSResponse  dssResponse = segmentDocument(createDSSRequest(ccdXml, xacmlPolicy, purposeOfUse));
            redactedDocXML = dssResponse.getSegmentedDocumentXml();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (SimpleMarshallerException e) {
            logger.error(e.getMessage(), e);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
        }
        return redactedDocXML;
    }

    @Override
    public String getSegmentDocXML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws DSSException {
        String SegmentDocXML = null;
        try {
            DSSResponse  dssResponse = segmentDocument(createDSSRequest(ccdXml, xacmlPolicy, purposeOfUse));
            SegmentDocXML = dssResponse.getTryPolicyDocumentXml();
         } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (SimpleMarshallerException e) {
            logger.error(e.getMessage(), e);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
        }
        return SegmentDocXML;
    }





    private DSSResponse segmentDocument(DSSRequest request) {
        DSSResponse dssResponse = (DSSResponse) webServiceTemplate.marshalSendAndReceive(request);
        return dssResponse;
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

        final String output = xmlTransformer.transform(taggedC32Doc,
                xslUrl, Optional.empty(), Optional.empty());

        logger.info("Printing transformed xslt: " + output);
        return output;


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

    private DSSRequest createDSSRequest(String ccdXml, String xacmlPolicy, String purposeOfUse) throws IOException, SimpleMarshallerException, URISyntaxException {
        DSSRequest request = new DSSRequest();

        PdpRequestResponse pdpRequestResponse = contextHandler
                .makeDecisionForTryingPolicy(xacmlPolicy, purposeOfUse);
        XacmlResult xacmlResult = createXacmlResult(pdpRequestResponse.getXacmlRequest(),
                pdpRequestResponse.getXacmlResponse());

        String enforcementPolicies = marshaller.marshal(xacmlResult);

        request.setDocumentXml(ccdXml);
        request.setEnforcementPoliciesXml(enforcementPolicies);
        request.setAudited(false);
        request.setAuditFailureByPass(true);
        request.setEnableTryPolicyResponse(Boolean.TRUE);

        return request;
    }

    private XacmlResult createXacmlResult(XacmlRequest xacmlRequest,
                                          XacmlResponse xacmlResponse) {
        final XacmlResult xacmlResult = new XacmlResult();
        xacmlResult.setHomeCommunityId(xacmlRequest.getHomeCommunityId());
        xacmlResult.setMessageId(xacmlRequest.getMessageId());
        xacmlResult.setPdpDecision(xacmlResponse.getPdpDecision());
        xacmlResult.setPdpObligations(xacmlResponse.getPdpObligation());
        xacmlResult.setSubjectPurposeOfUse(SubjectPurposeOfUse
                .fromAbbreviation(xacmlRequest.getPurposeOfUse()));
        xacmlResult.setPatientId(xacmlRequest.getPatientId());
        return xacmlResult;
    }
}
