package gov.samhsa.c2s.trypolicy.service.dto;

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
public class DSSResponseForDirect {

    @XmlElement(required = true)
    protected String segmentedDocumentXml;
    @XmlElement(required = true)
    protected String postSegmentationMetadataXml;
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler documentPayloadRawData;

    /**
     * Gets the value of the segmentedDocumentXml property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSegmentedDocumentXml() {
        return segmentedDocumentXml;
    }

    /**
     * Sets the value of the segmentedDocumentXml property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSegmentedDocumentXml(String value) {
        this.segmentedDocumentXml = value;
    }

    /**
     * Gets the value of the postSegmentationMetadataXml property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPostSegmentationMetadataXml() {
        return postSegmentationMetadataXml;
    }

    /**
     * Sets the value of the postSegmentationMetadataXml property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPostSegmentationMetadataXml(String value) {
        this.postSegmentationMetadataXml = value;
    }

    /**
     * Gets the value of the documentPayloadRawData property.
     *
     * @return possible object is
     * {@link DataHandler }
     */
    public DataHandler getDocumentPayloadRawData() {
        return documentPayloadRawData;
    }

    /**
     * Sets the value of the documentPayloadRawData property.
     *
     * @param value allowed object is
     *              {@link DataHandler }
     */
    public void setDocumentPayloadRawData(DataHandler value) {
        this.documentPayloadRawData = value;
    }
}