package gov.samhsa.mhc.trypolicy.config;


import gov.samhsa.mhc.common.document.converter.DocumentXmlConverter;
import gov.samhsa.mhc.common.document.converter.DocumentXmlConverterImpl;
import gov.samhsa.mhc.common.document.transformer.XmlTransformer;
import gov.samhsa.mhc.common.document.transformer.XmlTransformerImpl;
import gov.samhsa.mhc.common.marshaller.SimpleMarshaller;
import gov.samhsa.mhc.common.marshaller.SimpleMarshallerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextConfig {

    @Bean
    public DocumentXmlConverter documentXmlConverter() {
        return new DocumentXmlConverterImpl();
    }

    @Bean
    public XmlTransformer xmlTransformer() {
        return new XmlTransformerImpl(simpleMarshallerImpl());
    }

    @Bean
    public SimpleMarshaller simpleMarshallerImpl() {
        return new SimpleMarshallerImpl();
    }
}
