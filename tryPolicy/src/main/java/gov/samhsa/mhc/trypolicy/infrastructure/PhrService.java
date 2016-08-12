package gov.samhsa.mhc.trypolicy.infrastructure;

import gov.samhsa.mhc.trypolicy.config.OAuth2FeignClientConfig;
import gov.samhsa.mhc.trypolicy.infrastructure.dto.PatientDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "phr", configuration = OAuth2FeignClientConfig.class)
public interface PhrService {

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    PatientDto getPatient();
}