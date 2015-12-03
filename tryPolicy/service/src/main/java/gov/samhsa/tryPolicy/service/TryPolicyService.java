package gov.samhsa.tryPolicy.service;

import gov.samhsa.tryPolicy.exception.TryPolicyException;

/**
 * Created by sadhana.chandra on 11/16/2015.
 */
public interface TryPolicyService {


    public String getSegmentDocXHTML(String ccdXml, String xacmlPolicy,
              String purposeOfUse) throws TryPolicyException;

    public String getRedactedDocXHTML(String ccdXml, String xacmlPolicy,
                            String purposeOfUse) throws TryPolicyException;

    public String getSegmentDocXML(String ccdXml, String xacmlPolicy,
                                     String purposeOfUse) throws TryPolicyException;

    public String getRedactedDocXML(String ccdXml, String xacmlPolicy,
                                      String purposeOfUse) throws TryPolicyException;
}
