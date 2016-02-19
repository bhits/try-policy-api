
package gov.samhsa.mhc.trypolicy.service.dto;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documentXml" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="enforcementPoliciesXml" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="audited" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="auditFailureByPass" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="senderEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="recipientEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="xdsDocumentEntryUniqueId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="packageAsXdm" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "documentXml",
    "enforcementPoliciesXml",
    "audited",
    "auditFailureByPass",
    "senderEmailAddress",
    "recipientEmailAddress",
    "xdsDocumentEntryUniqueId",
    "packageAsXdm"
})
@XmlRootElement(name = "DSSRequestForDirect")
public class DSSRequestForDirect {

    @XmlElement(required = true)
    protected String documentXml;
    @XmlElement(required = true)
    protected String enforcementPoliciesXml;
    protected Boolean audited;
    protected Boolean auditFailureByPass;
    @XmlElement(required = true)
    protected String senderEmailAddress;
    @XmlElement(required = true)
    protected String recipientEmailAddress;
    @XmlElement(required = true)
    protected String xdsDocumentEntryUniqueId;
    protected boolean packageAsXdm;

    /**
     * Gets the value of the documentXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentXml() {
        return documentXml;
    }

    /**
     * Sets the value of the documentXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentXml(String value) {
        this.documentXml = value;
    }

    /**
     * Gets the value of the enforcementPoliciesXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnforcementPoliciesXml() {
        return enforcementPoliciesXml;
    }

    /**
     * Sets the value of the enforcementPoliciesXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnforcementPoliciesXml(String value) {
        this.enforcementPoliciesXml = value;
    }

    /**
     * Gets the value of the audited property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAudited() {
        return audited;
    }

    /**
     * Sets the value of the audited property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAudited(Boolean value) {
        this.audited = value;
    }

    /**
     * Gets the value of the auditFailureByPass property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAuditFailureByPass() {
        return auditFailureByPass;
    }

    /**
     * Sets the value of the auditFailureByPass property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAuditFailureByPass(Boolean value) {
        this.auditFailureByPass = value;
    }

    /**
     * Gets the value of the senderEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderEmailAddress() {
        return senderEmailAddress;
    }

    /**
     * Sets the value of the senderEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderEmailAddress(String value) {
        this.senderEmailAddress = value;
    }

    /**
     * Gets the value of the recipientEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientEmailAddress() {
        return recipientEmailAddress;
    }

    /**
     * Sets the value of the recipientEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientEmailAddress(String value) {
        this.recipientEmailAddress = value;
    }

    /**
     * Gets the value of the xdsDocumentEntryUniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXdsDocumentEntryUniqueId() {
        return xdsDocumentEntryUniqueId;
    }

    /**
     * Sets the value of the xdsDocumentEntryUniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXdsDocumentEntryUniqueId(String value) {
        this.xdsDocumentEntryUniqueId = value;
    }

    /**
     * Gets the value of the packageAsXdm property.
     * 
     */
    public boolean isPackageAsXdm() {
        return packageAsXdm;
    }

    /**
     * Sets the value of the packageAsXdm property.
     * 
     */
    public void setPackageAsXdm(boolean value) {
        this.packageAsXdm = value;
    }

}
