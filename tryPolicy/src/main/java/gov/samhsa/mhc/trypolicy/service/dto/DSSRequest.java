package gov.samhsa.mhc.trypolicy.service.dto;



import javax.validation.constraints.NotNull;

public class DSSRequest {

    @NotNull
    protected XacmlResult xacmlResult;
    protected boolean  audited = false;
    protected boolean auditFailureByPass = false;
    protected boolean  enableTryPolicyResponse =false;
    @NotNull
    private byte[] document;
    private String  documentEncoding = "UTF-8";

    public XacmlResult getXacmlResult() {
        return xacmlResult;
    }

    public void setXacmlResult(XacmlResult xacmlResult) {
        this.xacmlResult = xacmlResult;
    }

    public boolean isAudited() {
        return audited;
    }

    public void setAudited(boolean audited) {
        this.audited = audited;
    }

    public boolean isAuditFailureByPass() {
        return auditFailureByPass;
    }

    public void setAuditFailureByPass(boolean auditFailureByPass) {
        this.auditFailureByPass = auditFailureByPass;
    }

    public boolean isEnableTryPolicyResponse() {
        return enableTryPolicyResponse;
    }

    public void setEnableTryPolicyResponse(boolean enableTryPolicyResponse) {
        this.enableTryPolicyResponse = enableTryPolicyResponse;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getDocumentEncoding() {
        return documentEncoding;
    }

    public void setDocumentEncoding(String documentEncoding) {
        this.documentEncoding = documentEncoding;
    }
}
