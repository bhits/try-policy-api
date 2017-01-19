package gov.samhsa.c2s.trypolicy.infrastructure;

import gov.samhsa.c2s.trypolicy.config.OAuth2FeignClientConfig;
import gov.samhsa.c2s.trypolicy.service.dto.CCDDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "pcm", configuration = OAuth2FeignClientConfig.class)
public interface PcmService {

    @RequestMapping(value = "/patients/clinicaldocuments/ccd/{documentId}", method = RequestMethod.GET)
    CCDDto getCCDByDocumentId(@PathVariable("documentId") String documentId);

    @RequestMapping(value = "/patients/consents/{consentId}/obligations", method = RequestMethod.GET)
    List<String> getObligationsByConsentId(@PathVariable("consentId") String consentId);
}