package gov.samhsa.c2s.trypolicy.service;

import gov.samhsa.c2s.trypolicy.service.dto.TryPolicyResponse;

public interface TryPolicyService {
    TryPolicyResponse getSegmentDocXHTML(String documentId, String consentId, String purposeOfUse);
}