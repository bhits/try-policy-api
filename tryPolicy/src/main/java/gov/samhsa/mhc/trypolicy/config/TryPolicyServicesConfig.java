package gov.samhsa.mhc.trypolicy.config;


import gov.samhsa.mhc.trypolicy.service.TryPolicyService;
import gov.samhsa.mhc.trypolicy.service.TryPolicyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TryPolicyServicesConfig {

    @Bean
    public TryPolicyService tryPolicyService() {
        return new TryPolicyServiceImpl();
    }

    @Bean
    public DSSProperties dssProperties(){ return new DSSProperties();}
}
