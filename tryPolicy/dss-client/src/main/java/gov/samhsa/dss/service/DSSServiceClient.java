package gov.samhsa.dss.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by sadhana.chandra on 11/18/2015.
 */
@Component
public class DSSServiceClient implements CommandLineRunner {

    @Autowired
    DSSService dssService;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("DSSServiceClient:run() Start");

        String c32Xml= FileUtils.readFileToString(new File(
                getClass().getClassLoader()
                        .getResource("dssC32.xml").toURI()));
        String xacmlPolicy=FileUtils.readFileToString(new File(
                getClass().getClassLoader()
                        .getResource("dssXacml.xml").toURI()));
        String purposeOfUse = "TREATMENT";

         String taggedC32 = dssService.getSegmentedDoc(c32Xml, xacmlPolicy, purposeOfUse);

        System.out.println(taggedC32);
    }


}
