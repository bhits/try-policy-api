package gov.samhsa.dss.service;

import gov.samhsa.dss.exception.DSSException;
import org.springframework.stereotype.Service;

/**
 * Created by sadhana.chandra on 11/18/2015.
 */
@Service
public interface DSSService {

    public String getSegmentDocXHTML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws DSSException;
    public String getSegmentDocXML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws DSSException;

    public String getRedactedDocXHTML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws DSSException;
    public String getRedactedDocXML(String ccdXml, String xacmlPolicy, String purposeOfUse) throws DSSException;


    //public String getTryPolicyDoc(String ccdXml, String xacmlPolicy, String purposeOfUse) throws DSSException;
   // public String getSegmentedDoc(String ccdXml, String xacmlPolicy, String purposeOfUse) throws DSSException;

   // public DSSResponse segmentDocument(DSSRequest request) throws DSSException;
   // public String getTaggedC32(String segmentedC32) throws DSSException;
}
