package gov.samhsa.c2s.trypolicy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dss.segment")
@Data
public class DSSProperties {

    private String defaultIsAudited;
    private String defaultIsAuditFailureByPass;
    private String pdpDecision;
    private String homeCommunityId;
    private String documentEncoding;
}