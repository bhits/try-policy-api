package gov.samhsa.mhc.trypolicy.service;


import gov.samhsa.mhc.trypolicy.exception.TryPolicyException;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
public interface TryPolicyService {


    public String getSegmentDocXHTML(String patientUserName, String patientId, String documentId, String consentId, String purposeOfUse) throws TryPolicyException;

}
