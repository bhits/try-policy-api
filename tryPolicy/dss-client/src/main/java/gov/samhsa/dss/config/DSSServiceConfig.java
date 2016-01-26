package gov.samhsa.dss.config;

import ch.qos.logback.audit.AuditException;
import gov.samhsa.acs.audit.AuditService;
import gov.samhsa.acs.audit.AuditServiceImpl;
import gov.samhsa.acs.common.tool.*;
import gov.samhsa.acs.contexthandler.*;
import gov.samhsa.acs.contexthandler.pg.PolicyDecisionPointImpl;
import gov.samhsa.acs.contexthandler.pg.RequestGenerator;
import gov.samhsa.acs.polrep.client.PolRepRestClient;
import gov.samhsa.consent2share.hl7.Hl7v3Transformer;
import gov.samhsa.consent2share.hl7.Hl7v3TransformerImpl;
import gov.samhsa.consent2share.pixclient.service.PixManagerService;
import gov.samhsa.consent2share.pixclient.service.PixManagerServiceImpl;
import gov.samhsa.consent2share.pixclient.util.PixManagerMessageHelper;
import gov.samhsa.consent2share.pixclient.util.PixManagerRequestXMLToJava;
import gov.samhsa.dss.service.DSSService;
import gov.samhsa.dss.service.DSSServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by sadhana.chandra on 11/18/2015.
 */
@Configuration
@ConfigurationProperties(prefix = "polrep")
public class DSSServiceConfig {

    @Autowired
    DSSPolRepSettings dssPolRepSettings;

    @Bean
    public DSSPolRepSettings dssPolRepSettings() {
        return new DSSPolRepSettings();
    }

    @Bean
    public DSSService dssService() {
        return new DSSServiceImpl();
    }


    /*<bean id="documentXmlConverterImpl" class="gov.samhsa.acs.common.tool.DocumentXmlConverterImpl" />
    * <bean id="requestGenerator" class="gov.samhsa.acs.contexthandler.RequestGenerator" />
    * <bean id="documentAccessorImpl" class="gov.samhsa.acs.common.tool.DocumentAccessorImpl" />
    * 	<!-- ACS Audit Service -->
	<bean id="auditServiceImpl" class="gov.samhsa.acs.audit.AuditServiceImpl"
		scope="singleton" init-method="init" destroy-method="destroy">
		<constructor-arg value="AcsAuditService" />
	</bean>
	<bean id="polRepPolicyProvider" class="gov.samhsa.acs.contexthandler.PolRepPolicyProvider"
		scope="prototype" autowire="constructor">
		<constructor-arg index="0" value="${pidDomainId}"/>
		<constructor-arg index="1" value="${pidDomainType}"/>
	</bean>
	*/

    @Bean
    public DocumentXmlConverter documentXmlConverter() {
        return new DocumentXmlConverterImpl();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public RequestGenerator requestGenerator() {
        return new RequestGenerator();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public DocumentAccessor documentAccessor() {
        return new DocumentAccessorImpl();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public AuditService auditService() throws AuditException {
        return new AuditServiceImpl("AcsAuditService");
    }

/*    <!-- PDP: PolRepPolicyProvider: enable for Policy Repository integration -->
    <bean id="hl7v3TransformerImpl" class="gov.samhsa.consent2share.hl7.Hl7v3TransformerImpl"
    scope="prototype" autowire="constructor"/>
    <bean id="pixManagerRequestXMLToJava" class="gov.samhsa.consent2share.pixclient.util.PixManagerRequestXMLToJava"
    scope="prototype" autowire="constructor"/>
    <bean id="pixManagerMessageHelper" class="gov.samhsa.consent2share.pixclient.util.PixManagerMessageHelper"
    scope="prototype"/>
    <bean id="pixManagerServiceImpl" class="gov.samhsa.consent2share.pixclient.service.PixManagerServiceImpl"
    scope="prototype">
    <constructor-arg index="0" value="${empi}"/>
    </bean>
    <bean id="polRepRestClient" class="gov.samhsa.acs.polrep.client.PolRepRestClient"
    scope="prototype">
    <constructor-arg index="0" value="${c2sPolrepScheme}"/>
    <constructor-arg index="1" value="${c2sPolrepHost}"/>
    <constructor-arg index="2" value="${c2sPolrepPort}"/>
    <constructor-arg index="3" value="${c2sPolrepContext}"/>
    <constructor-arg index="4" value="${c2sPolrepVersion}"/>
    </bean>
    <bean id="polRepPolicyProvider" class="gov.samhsa.acs.contexthandler.PolRepPolicyProvider"
    scope="prototype" autowire="constructor">
    <constructor-arg index="0" value="${pidDomainId}"/>
    <constructor-arg index="1" value="${pidDomainType}"/>
    </bean>*/

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public SimpleMarshaller simpleMarshallerImpl() {
        return new SimpleMarshallerImpl();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public XmlTransformer xmlTransformer(SimpleMarshaller marshaller) {
        return new XmlTransformerImpl(marshaller);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public Hl7v3Transformer hl7v3Transformer(XmlTransformer xmlTransformer) {
        return new Hl7v3TransformerImpl(xmlTransformer);
    }


    /*    <bean id="simpleMarshallerImpl" class="gov.samhsa.acs.common.tool.SimpleMarshallerImpl"
        scope="prototype" />*/


    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public PixManagerRequestXMLToJava pixManagerRequestXMLToJava(SimpleMarshaller marshaller) {
        return new PixManagerRequestXMLToJava(marshaller);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public PolRepRestClient polRepRestClient() {

        return new PolRepRestClient(dssPolRepSettings.getC2sPolrepScheme(), dssPolRepSettings.getC2sPolrepHost(), Integer.parseInt(dssPolRepSettings.getC2sPolrepPort()), dssPolRepSettings.getC2sPolrepContext(), dssPolRepSettings.getC2sPolrepVersion());
    }


    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public PixManagerMessageHelper pixManagerMessageHelper() {
        return new PixManagerMessageHelper();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public PixManagerService pixManagerService(){
        return new PixManagerServiceImpl(dssPolRepSettings.getEmpiEndPtUrl());
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public PolicyProvider policyProvider(Hl7v3Transformer hl7v3Transformer,
                                         PixManagerRequestXMLToJava requestXMLToJava,
                                         PixManagerMessageHelper pixManagerMessageHelper,
                                         PixManagerService pixManagerService,
                                         PolRepRestClient polRepRestClient) {
        return new PolRepPolicyProvider(dssPolRepSettings.getPidDomainId(), dssPolRepSettings.getPidDomainType(), hl7v3Transformer, requestXMLToJava, pixManagerMessageHelper, pixManagerService, polRepRestClient);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public PolicyDecisionPoint policyDecisionPoint(PolicyProvider policyProvider,
                                                   RequestGenerator requestGenerator,
                                                   DocumentAccessor documentAccessor,
                                                   DocumentXmlConverter documentXmlConverter, AuditService auditService) {
        return new PolicyDecisionPointImpl(policyProvider,
                requestGenerator,
                documentAccessor,
                documentXmlConverter, auditService);
    }


    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Autowired(required = true)
    public ContextHandler contextHandler(PolicyDecisionPoint policyDecisionPoint, SimpleMarshaller marshaller) {
        return new ContextHandlerImpl(policyDecisionPoint, marshaller);
    }


}
