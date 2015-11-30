package gov.samhsa.tryPolicy.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "gov.samhsa.tryPolicy" })
public class TryPolicyWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.out.println("WebApplication:main() Start");
        Object[] configClasses = {TryPolicyWebApplication.class , TryPolicyWebConfig.class};
        SpringApplication.run(configClasses, args);
        System.out.println("WebApplication:main() End");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TryPolicyWebApplication.class);
    }

}
