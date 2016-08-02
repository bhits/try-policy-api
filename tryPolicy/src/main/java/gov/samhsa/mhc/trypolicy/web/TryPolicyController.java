package gov.samhsa.mhc.trypolicy.web;

import gov.samhsa.mhc.trypolicy.service.TryPolicyService;
import gov.samhsa.mhc.trypolicy.service.dto.TryPolicyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class TryPolicyController {

    /**
     * The logger.
     */
    @Autowired
    private TryPolicyService tryPolicyService;

    @RequestMapping(value = "/tryPolicyXHTML", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TryPolicyResponse tryPolicyByConsentIdXHTML(@RequestParam("patientUserName") String patientUserName,
                                                       @RequestParam("patientId") String patientId,
                                                       @RequestParam("documentId") String documentId,
                                                       @RequestParam("consentId") String consentId,
                                                       @RequestParam("purposeOfUseCode") String purposeOfUseCode) {
        return tryPolicyService.getSegmentDocXHTML(patientUserName, patientId, documentId, consentId, purposeOfUseCode);
    }
}