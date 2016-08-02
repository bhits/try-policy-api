package gov.samhsa.mhc.trypolicy.service;

import gov.samhsa.mhc.trypolicy.service.dto.TryPolicyResponse;

public interface TryPolicyService {
    TryPolicyResponse getSegmentDocXHTML(String patientUsername, String patientId, String documentId, String consentId, String purposeOfUse);
}
