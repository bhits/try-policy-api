package gov.samhsa.mhc.trypolicy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TryPolicyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TryPolicyApplication.class, args);
    }
}
