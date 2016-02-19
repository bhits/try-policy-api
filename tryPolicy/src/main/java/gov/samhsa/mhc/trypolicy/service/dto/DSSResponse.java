package gov.samhsa.mhc.trypolicy.service.dto;

public class DSSResponse {

    private byte[] segmentedDocument;
    private byte[] tryPolicyDocument;
    private String encoding;

    public byte[] getSegmentedDocument() {
        return segmentedDocument;
    }

    public void setSegmentedDocument(byte[] segmentedDocument) {
        this.segmentedDocument = segmentedDocument;
    }

    public byte[] getTryPolicyDocument() {
        return tryPolicyDocument;
    }

    public void setTryPolicyDocument(byte[] tryPolicyDocument) {
        this.tryPolicyDocument = tryPolicyDocument;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
