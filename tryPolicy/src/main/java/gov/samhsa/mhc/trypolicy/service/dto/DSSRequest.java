package gov.samhsa.mhc.trypolicy.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DSSRequest {

    @NotNull
    private XacmlResult xacmlResult;
    private boolean audited;
    private boolean auditFailureByPass;
    private boolean enableTryPolicyResponse;
    @NotNull
    private byte[] document;
    private String documentEncoding;
}
