package gov.samhsa.c2s.trypolicy.service.dto;

import lombok.Data;

@Data
public class DSSResponse {
    private byte[] segmentedDocument;
    private byte[] tryPolicyDocument;
    private String encoding;
    private boolean isCCDADocument;
}