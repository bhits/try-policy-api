package gov.samhsa.c2s.trypolicy.service.dto;

import lombok.Data;

import javax.activation.DataHandler;
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
 *         &lt;element name="segmentedDocumentXml" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="postSegmentationMetadataXml" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="documentPayloadRawData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "segmentedDocumentXml",
        "postSegmentationMetadataXml",
        "documentPayloadRawData"
})
@XmlRootElement(name = "DSSResponseForDirect")
@Data
public class DSSResponseForDirect {

    @XmlElement(required = true)
    protected String segmentedDocumentXml;
    @XmlElement(required = true)
    protected String postSegmentationMetadataXml;
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler documentPayloadRawData;
}