package gov.samhsa.mhc.trypolicy;

import gov.samhsa.mhc.trypolicy.config.TryPolicyWebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TryPolicyApplication {

    public static void main(String[] args) {
        System.out.println("TryPolicyApplication:main() Start");
        Object[] configClasses = {TryPolicyApplication.class, TryPolicyWebConfig.class};
        SpringApplication.run(configClasses, args);
        System.out.println("TryPolicyApplication:main() End");
    }


}
