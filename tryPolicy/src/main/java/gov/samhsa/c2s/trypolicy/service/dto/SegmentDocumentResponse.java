package gov.samhsa.c2s.trypolicy.service.dto;

import lombok.Data;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.*;

/**
 * <p>Java class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentPayloadRawData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="segmentedDocumentXml" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tryPolicyDocumentXml" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="postSegmentationMetadataXml" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="executionResponseContainerXml" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "documentPayloadRawData",
        "segmentedDocumentXml",
        "tryPolicyDocumentXml",
        "postSegmentationMetadataXml",
        "executionResponseContainerXml"
})
@XmlRootElement(name = "SegmentDocumentResponse")
@Data
public class SegmentDocumentResponse {
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler documentPayloadRawData;
    @XmlElement(required = true)
    protected String segmentedDocumentXml;
    @XmlElement(required = true)
    protected String tryPolicyDocumentXml;
    @XmlElement(required = true)
    protected String postSegmentationMetadataXml;
    @XmlElement(required = true)
    protected String executionResponseContainerXml;
}