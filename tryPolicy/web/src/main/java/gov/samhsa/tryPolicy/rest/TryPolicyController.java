package gov.samhsa.tryPolicy.rest;

import gov.samhsa.tryPolicy.exception.TryPolicyException;
import gov.samhsa.tryPolicy.service.TryPolicyService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
@RestController
@RequestMapping("/policies")
public class TryPolicyController {

   @Autowired
   private TryPolicyService tryPolicyService;

    /**
     * The logger.
     */
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/byXacml", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String tryPolicyByXacml(String ccdXml, String xacmlPolicy, String purposeOfUse) throws TryPolicyException {
        String tryPolicy = "tryPolicy";
        try {
            ccdXml= FileUtils.readFileToString(new File(
                    getClass().getClassLoader()
                            .getResource("c32.xml").toURI()));

         xacmlPolicy=FileUtils.readFileToString(new File(
                getClass().getClassLoader()
                        .getResource("xacml.xml").toURI()));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
           throw new TryPolicyException(e.getMessage(), e);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
            throw new TryPolicyException(e.getMessage(), e);
        }
        purposeOfUse = "TREATMENT";
        tryPolicy = tryPolicyService.getSegmentDocXHTML("", ccdXml, xacmlPolicy, purposeOfUse);
        return tryPolicy;
    }

    @RequestMapping(value="/byConsentIdXHTML/{patientUserName}/{documentId}/{consentId}/{purposeOfUseCode}", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String tryPolicyByConsentIdXHTML(@PathVariable("patientUserName")  String patientUserName, @PathVariable("documentId")  String documentId, @PathVariable("consentId")  String consentId, @PathVariable("purposeOfUseCode")  String purposeOfUseCode)throws TryPolicyException {

        String tryPolicyXHTML = "";
        try {
            tryPolicyXHTML = tryPolicyService.getSegmentDocXHTML(patientUserName, documentId, consentId, purposeOfUseCode);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new TryPolicyException(e.getMessage(), e);
        }

        return tryPolicyXHTML;
    }

    @RequestMapping(value="/byConsentIdXML/{ccdXml}/{consentId}/{purposeOfUse}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String tryPolicyByConsentIdXMLMock(@PathVariable String ccdXml, @PathVariable String consentId, @PathVariable String purposeOfUse) throws TryPolicyException {
        String tryPolicy = "tryPolicy";
        String xacmlPolicy = consentId;
        try {
            ccdXml= FileUtils.readFileToString(new File(
                    getClass().getClassLoader()
                            .getResource("c32.xml").toURI()));

            xacmlPolicy=FileUtils.readFileToString(new File(
                    getClass().getClassLoader()
                            .getResource("xacml.xml").toURI()));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new TryPolicyException(e.getMessage(), e);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
            throw new TryPolicyException(e.getMessage(), e);
        }
        purposeOfUse = "TREATMENT";
        tryPolicy = tryPolicyService.getSegmentDocXML(ccdXml, xacmlPolicy, purposeOfUse);
        return tryPolicy;
    }


    @RequestMapping(value="/redactByXacml", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String redactByXacml(String ccdXml, String xacmlPolicy,
                            String purposeOfUse) throws TryPolicyException {
        String segmentPHR = "segmentPHR";
        try {
            ccdXml= (null != ccdXml) ? ccdXml : FileUtils.readFileToString(new File(
                    getClass().getClassLoader()
                            .getResource("c32.xml").toURI()));

            xacmlPolicy=(null != xacmlPolicy) ? xacmlPolicy : FileUtils.readFileToString(new File(
                    getClass().getClassLoader()
                            .getResource("xacml.xml").toURI()));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new TryPolicyException(e.getMessage(), e);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
            throw new TryPolicyException(e.getMessage(), e);
        }
        purposeOfUse = "TREATMENT";
        segmentPHR = tryPolicyService.getRedactedDocXHTML(ccdXml, xacmlPolicy, purposeOfUse);
        return segmentPHR;
    }

}
