package gov.samhsa.mhc.trypolicy.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dss.segment")
public class DSSProperties {

    private String defaultIsAudited;
    private String defaultIsAuditFailureByPass;
    private String pdpDecision;
    private String homeCommunityId;
    private String documentEncoding;

    public String getDefaultIsAudited() {
        return defaultIsAudited;
    }

    public void setDefaultIsAudited(String defaultIsAudited) {
        this.defaultIsAudited = defaultIsAudited;
    }

    public String getDefaultIsAuditFailureByPass() {
        return defaultIsAuditFailureByPass;
    }

    public void setDefaultIsAuditFailureByPass(String defaultIsAuditFailureByPass) {
        this.defaultIsAuditFailureByPass = defaultIsAuditFailureByPass;
    }

    public String getPdpDecision() {
        return pdpDecision;
    }

    public void setPdpDecision(String pdpDecision) {
        this.pdpDecision = pdpDecision;
    }

    public String getHomeCommunityId() {
        return homeCommunityId;
    }

    public void setHomeCommunityId(String homeCommunityId) {
        this.homeCommunityId = homeCommunityId;
    }

    public String getDocumentEncoding() {
        return documentEncoding;
    }

    public void setDocumentEncoding(String documentEncoding) {
        this.documentEncoding = documentEncoding;
    }
}
