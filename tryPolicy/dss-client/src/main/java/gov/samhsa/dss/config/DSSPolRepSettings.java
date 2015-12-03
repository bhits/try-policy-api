package gov.samhsa.dss.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sadhana.chandra on 11/19/2015.
 */
@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@ConfigurationProperties(prefix="polrep")
public class DSSPolRepSettings {

    private String c2sPolrepScheme;
    private String c2sPolrepHost;
    private String c2sPolrepPort;
    private String c2sPolrepContext;
    private String c2sPolrepVersion;
    private String pidDomainId;
    private String pidDomainType;
    private String empiEndPtUrl;


    public String getC2sPolrepScheme() {
        return c2sPolrepScheme;
    }

    public void setC2sPolrepScheme(String c2sPolrepScheme) {
        this.c2sPolrepScheme = c2sPolrepScheme;
    }

    public String getC2sPolrepHost() {
        return c2sPolrepHost;
    }

    public void setC2sPolrepHost(String c2sPolrepHost) {
        this.c2sPolrepHost = c2sPolrepHost;
    }

    public String getC2sPolrepPort() {
        return c2sPolrepPort;
    }

    public void setC2sPolrepPort(String c2sPolrepPort) {
        this.c2sPolrepPort = c2sPolrepPort;
    }

    public String getC2sPolrepContext() {
        return c2sPolrepContext;
    }

    public void setC2sPolrepContext(String c2sPolrepContext) {
        this.c2sPolrepContext = c2sPolrepContext;
    }

    public String getC2sPolrepVersion() {
        return c2sPolrepVersion;
    }

    public void setC2sPolrepVersion(String c2sPolrepVersion) {
        this.c2sPolrepVersion = c2sPolrepVersion;
    }

    public String getPidDomainId() {
        return pidDomainId;
    }

    public void setPidDomainId(String pidDomainId) {
        this.pidDomainId = pidDomainId;
    }

    public String getPidDomainType() {
        return pidDomainType;
    }

    public void setPidDomainType(String pidDomainType) {
        this.pidDomainType = pidDomainType;
    }

    public String getEmpiEndPtUrl() {
        return empiEndPtUrl;
    }

    public void setEmpiEndPtUrl(String empiEndPtUrl) {
        this.empiEndPtUrl = empiEndPtUrl;
    }
}
