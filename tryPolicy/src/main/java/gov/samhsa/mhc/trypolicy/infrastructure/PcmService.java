package gov.samhsa.mhc.trypolicy.infrastructure;

import gov.samhsa.mhc.trypolicy.service.dto.CCDDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("pcm")
public interface PcmService {

    @RequestMapping(value = "/patients/clinicaldocuments/ccd/{patientUsername}/{documentId}", method = RequestMethod.GET)
    CCDDto getCCDByPatientUsernameAndDocumentId(@PathVariable("patientUsername") String patientUsername,
                                                @PathVariable("documentId") String documentId);

    @RequestMapping(value = "/patients/{patientUsername}/consents/{consentId}/obligations", method = RequestMethod.GET)
    List<String> getObligationsByPatientUsernameAndConsentId(@PathVariable("patientUsername") String patientUsername,
                                                             @PathVariable("consentId") String consentId);
}
