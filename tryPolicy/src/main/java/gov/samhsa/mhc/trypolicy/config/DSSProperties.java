package gov.samhsa.mhc.trypolicy.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="dss.segment")
public class DSSProperties {

    private String dssUrl;
    private String xacmlUrl;
    private String ccdUrl;
    private String defaultIsAudited;
    private String defaultIsAuditFailureByPass;
    private String pdpDecision;
    private String homeCommunityId;

    public String getDssUrl() {
        return dssUrl;
    }

    public void setDssUrl(String dssUrl) {
        this.dssUrl = dssUrl;
    }

    public String getXacmlUrl() {
        return xacmlUrl;
    }

    public void setXacmlUrl(String xacmlUrl) {
        this.xacmlUrl = xacmlUrl;
    }

    public String getCcdUrl() {
        return ccdUrl;
    }

    public void setCcdUrl(String ccdUrl) {
        this.ccdUrl = ccdUrl;
    }

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
}
