package gov.samhsa.tryPolicy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
@Configuration
@Import({TryPolicyServicesConfig.class, TryPolicyAspectsConfig.class})
public class TryPolicyWebConfig {
}
