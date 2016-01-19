package gov.samhsa.tryPolicy.rest;

import gov.samhsa.tryPolicy.exception.TryPolicyException;
import gov.samhsa.tryPolicy.service.TryPolicyService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
@RestController
public class TryPolicyController {

   @Autowired
   private TryPolicyService tryPolicyService;


    @RequestMapping(value="/tryPolicyByXacml", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String tryPolicyByXacml(String ccdXml, String xacmlPolicy,
                                   String purposeOfUse) throws TryPolicyException {
        String tryPolicy = "tryPolicy";
        try {
            ccdXml= FileUtils.readFileToString(new File(
                    getClass().getClassLoader()
                            .getResource("c32.xml").toURI()));

         xacmlPolicy=FileUtils.readFileToString(new File(
                getClass().getClassLoader()
                        .getResource("xacml.xml").toURI()));
        } catch (IOException e) {
           throw new TryPolicyException(e.getMessage(), e);
        } catch (URISyntaxException e) {
            throw new TryPolicyException(e.getMessage(), e);
        }
        purposeOfUse = "TREATMENT";
        tryPolicy = tryPolicyService.getSegmentDocXHTML(ccdXml, xacmlPolicy, purposeOfUse);
        return tryPolicy;
    }


    @RequestMapping(value="/tryPolicyByConsentIdXHTMLMock/{ccdXml}/{consentId}/{purposeOfUse}", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String tryPolicyByConsentIdXHTMLMock(@PathVariable  String ccdXml, @PathVariable  String consentId, @PathVariable  String purposeOfUse)
            throws TryPolicyException {
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
            throw new TryPolicyException(e.getMessage(), e);
        } catch (URISyntaxException e) {
            throw new TryPolicyException(e.getMessage(), e);
        }
        purposeOfUse = "TREATMENT";
        tryPolicy = tryPolicyService.getSegmentDocXHTML(ccdXml, xacmlPolicy, purposeOfUse);

        return tryPolicy;
    }

    @RequestMapping(value="/tryPolicyByConsentIdXMLMock/{ccdXml}/{consentId}/{purposeOfUse}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
            throw new TryPolicyException(e.getMessage(), e);
        } catch (URISyntaxException e) {
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
            throw new TryPolicyException(e.getMessage(), e);
        } catch (URISyntaxException e) {
            throw new TryPolicyException(e.getMessage(), e);
        }
        purposeOfUse = "TREATMENT";
        segmentPHR = tryPolicyService.getRedactedDocXHTML(ccdXml, xacmlPolicy, purposeOfUse);
        return segmentPHR;
    }

}
