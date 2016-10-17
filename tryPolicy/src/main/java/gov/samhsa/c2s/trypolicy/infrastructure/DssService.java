package gov.samhsa.c2s.trypolicy.infrastructure;

import gov.samhsa.c2s.trypolicy.service.dto.DSSRequest;
import gov.samhsa.c2s.trypolicy.service.dto.DSSResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("dss")
public interface DssService {

    @RequestMapping(value = "/segmentedDocument", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    DSSResponse segmentDocument(@RequestBody DSSRequest request);
}