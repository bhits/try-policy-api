package gov.samhsa.dss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by sadhana.chandra on 11/17/2015.
 */
@Configuration
@Import({DSSWSConfig.class,DSSServiceConfig.class})
public class DSSClientConfig {


}
