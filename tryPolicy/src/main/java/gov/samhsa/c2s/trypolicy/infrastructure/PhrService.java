package gov.samhsa.c2s.trypolicy.infrastructure;

import gov.samhsa.c2s.trypolicy.infrastructure.dto.PatientDto;
import gov.samhsa.c2s.trypolicy.config.OAuth2FeignClientConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "phr", configuration = OAuth2FeignClientConfig.class)
public interface PhrService {

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    PatientDto getPatient();
}