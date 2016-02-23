package gov.samhsa.mhc.trypolicy.config;


import gov.samhsa.mhc.common.document.converter.DocumentXmlConverter;
import gov.samhsa.mhc.common.document.converter.DocumentXmlConverterImpl;
import gov.samhsa.mhc.common.document.transformer.XmlTransformer;
import gov.samhsa.mhc.common.document.transformer.XmlTransformerImpl;
import gov.samhsa.mhc.common.marshaller.SimpleMarshaller;
import gov.samhsa.mhc.common.marshaller.SimpleMarshallerImpl;
import gov.samhsa.mhc.trypolicy.service.TryPolicyService;
import gov.samhsa.mhc.trypolicy.service.TryPolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TryPolicyServicesConfig {

    @Bean
    public TryPolicyService tryPolicyService() {
        return new TryPolicyServiceImpl();
    }

    @Bean
    public DSSProperties dssProperties(){ return new DSSProperties();}


    @Bean
    public DocumentXmlConverter documentXmlConverter() {
        return new DocumentXmlConverterImpl();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public XmlTransformer xmlTransformer(SimpleMarshaller marshaller) {
        return new XmlTransformerImpl(marshaller);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public SimpleMarshaller simpleMarshallerImpl() {
        return new SimpleMarshallerImpl();
    }
}
