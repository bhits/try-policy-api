package gov.samhsa.c2s.trypolicy.service.dto;

import lombok.Data;

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
@Data
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
}