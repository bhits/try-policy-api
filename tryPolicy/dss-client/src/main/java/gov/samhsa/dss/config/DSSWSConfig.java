package gov.samhsa.dss.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

/**
 * Created by sadhana.chandra on 11/18/2015.
 */
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties
public class DSSWSConfig {

    @Value("${dss.serviceEndPointUrl}")
    String dssServiceEndPointUrl;
/*
       <!-- Define the SOAP version used by the WSDL -->
    <bean id="soapMessageFactory" class="org.springframework.ws.soap.saaj.SaajSoapMessageFactory">
    <property name="soapVersion">
    <util:constant static-field="org.springframework.ws.soap.SoapVersion.SOAP_12"/>
    </property>
    </bean>

    <!-- The location of the generated Java files -->
    <oxm:jaxb2-marshaller id="marshaller" contextPath="myproject.wsdl.currency"/>

    <!-- Configure Spring Web Services -->
    <bean id="webServiceTemplate" class="org.springframework.ws.client.core.WebServiceTemplate">
    <constructor-arg ref="soapMessageFactory"/>
    <property name="marshaller" ref="marshaller"/>
    <property name="unmarshaller" ref="marshaller"/>
    <property name="defaultUri" value="http://www.webservicex.net/CurrencyConvertor.asmx?WSDL"/>
    </bean>
*/

    @Bean
    public SaajSoapMessageFactory soapMessageFactory(){
        SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory();

        saajSoapMessageFactory.setSoapVersion(SoapVersion.SOAP_11);

        return saajSoapMessageFactory;
    }


    @Bean
    public Marshaller marshaller(){

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("gov.samhsa.acs.dss.ws.schema");
        return marshaller;
    }

    @Bean
    public Unmarshaller unMarshaller(){

        Jaxb2Marshaller unMarshaller = new Jaxb2Marshaller();
        unMarshaller.setContextPath("gov.samhsa.acs.dss.ws.schema");
        return unMarshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(){

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(soapMessageFactory());
        webServiceTemplate.setMarshaller(marshaller());
        webServiceTemplate.setUnmarshaller(unMarshaller());
        webServiceTemplate.setDefaultUri(dssServiceEndPointUrl);
        return webServiceTemplate;
    }

    public String getDssServiceEndPointUrl() {
        return dssServiceEndPointUrl;
    }

    public void setDssServiceEndPointUrl(String dssServiceEndPointUrl) {
        this.dssServiceEndPointUrl = dssServiceEndPointUrl;
    }
}
