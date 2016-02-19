package gov.samhsa.mhc.trypolicy.service;


import gov.samhsa.mhc.trypolicy.exception.TryPolicyException;

public interface TryPolicyService {


    String getSegmentDocXHTML(String patientUserName, String patientId, String documentId, String consentId, String purposeOfUse) throws TryPolicyException;

}
