package com.example.ozon;

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

        SpringApplication.run(OzonApplication.class, args);

    }

}
