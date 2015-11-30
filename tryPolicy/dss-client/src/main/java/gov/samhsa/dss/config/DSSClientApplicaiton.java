package gov.samhsa.dss.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by sadhana.chandra on 11/17/2015.
 */
@SpringBootApplication
@ComponentScan(basePackages = "gov.samhsa.dss")
public class DSSClientApplicaiton {


    public static void main(String[] args) {
        System.out.println("DSSClientApplicaiton:main() Start");
        Object[] configClasses = {DSSClientApplicaiton.class , DSSClientConfig.class};
        SpringApplication.run(configClasses,args);
        System.out.println("DSSClientApplicaiton:main() End");
    }

}
