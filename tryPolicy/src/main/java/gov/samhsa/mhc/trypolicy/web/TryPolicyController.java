package gov.samhsa.mhc.trypolicy.web;

import gov.samhsa.mhc.trypolicy.exception.TryPolicyException;
import gov.samhsa.mhc.trypolicy.service.TryPolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/policies")
public class TryPolicyController {

   @Autowired
   private TryPolicyService tryPolicyService;

    /**
     * The logger.
     */
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value="/byConsentIdXHTML/{patientUserName}/{patientId}/{documentId}/{consentId}/{purposeOfUseCode}", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String tryPolicyByConsentIdXHTML(@PathVariable("patientUserName")  String patientUserName, @PathVariable("patientId")  String patientId, @PathVariable("documentId")  String documentId, @PathVariable("consentId")  String consentId, @PathVariable("purposeOfUseCode")  String purposeOfUseCode)throws TryPolicyException {

        String tryPolicyXHTML;
        try {
            tryPolicyXHTML = tryPolicyService.getSegmentDocXHTML(patientUserName, patientId, documentId, consentId, purposeOfUseCode);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new TryPolicyException(e.getMessage(), e);
        }

        return tryPolicyXHTML;
    }



}
