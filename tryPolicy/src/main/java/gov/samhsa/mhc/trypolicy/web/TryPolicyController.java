package gov.samhsa.mhc.trypolicy.web;

import gov.samhsa.mhc.trypolicy.service.TryPolicyService;
import gov.samhsa.mhc.trypolicy.service.dto.TryPolicyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TryPolicyController {

    @Autowired
    private TryPolicyService tryPolicyService;

    @RequestMapping(value = "/tryPolicyXHTML", method = RequestMethod.GET)
    public TryPolicyResponse tryPolicyByConsentIdXHTML(@RequestParam("patientUserName") String patientUserName,
                                                       @RequestParam("patientId") String patientId,
                                                       @RequestParam("documentId") String documentId,
                                                       @RequestParam("consentId") String consentId,
                                                       @RequestParam("purposeOfUseCode") String purposeOfUseCode) {
        return tryPolicyService.getSegmentDocXHTML(patientUserName, patientId, documentId, consentId, purposeOfUseCode);
    }
}