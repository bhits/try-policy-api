package gov.samhsa.tryPolicy.rest;

import gov.samhsa.tryPolicy.exception.TryPolicyException;
import gov.samhsa.tryPolicy.service.TryPolicyService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
@RestController
public class TryPolicyController {

   @Autowired
   private TryPolicyService tryPolicyService;

    @RequestMapping(value="/tryPolicy", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String tryPolicy(String ccdXml, String xacmlPolicy,
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
        tryPolicy = tryPolicyService.tryPolicy(ccdXml, xacmlPolicy, purposeOfUse);
        return tryPolicy;
    }



    @RequestMapping(value="/segmentPHR", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String segmentPolicy(String ccdXml, String xacmlPolicy,
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
        segmentPHR = tryPolicyService.segmentPHR(ccdXml, xacmlPolicy, purposeOfUse);
        return segmentPHR;
    }

}
