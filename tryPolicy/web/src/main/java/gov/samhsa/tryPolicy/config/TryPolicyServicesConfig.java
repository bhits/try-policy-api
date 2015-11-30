package gov.samhsa.tryPolicy.config;

import gov.samhsa.dss.config.DSSClientConfig;
import gov.samhsa.dss.config.DSSPolRepSettings;
import gov.samhsa.tryPolicy.service.TryPolicyService;
import gov.samhsa.tryPolicy.service.TryPolicyServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
@Configuration
@EnableConfigurationProperties(DSSPolRepSettings.class)
@Import({DSSClientConfig.class})
public class TryPolicyServicesConfig {

    @Bean
    public TryPolicyService tryPolicyService() {
        return new TryPolicyServiceImpl();
    }

}
