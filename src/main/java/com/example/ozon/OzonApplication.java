package com.example.ozon;

import com.example.ozon.xml.XmlParserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class OzonApplication {

    public static void main(String[] args) {
        XmlParserService xmlParserService = new XmlParserService();
        try {
            System.out.println(xmlParserService.unmarshall().getDescription());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(OzonApplication.class, args);

    }

}
