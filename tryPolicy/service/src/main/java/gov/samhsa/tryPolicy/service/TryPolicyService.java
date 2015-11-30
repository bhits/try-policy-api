package gov.samhsa.tryPolicy.service;

import gov.samhsa.tryPolicy.exception.TryPolicyException;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
public interface TryPolicyService {


    public String tryPolicy(String c32Xml, String xacmlPolicy,
              String purposeOfUse) throws TryPolicyException;

}
