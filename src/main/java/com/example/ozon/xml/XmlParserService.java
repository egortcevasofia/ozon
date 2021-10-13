package com.example.ozon.xml;

import generated.Good;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class XmlParserService {

    public Good unmarshall() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Good.class);
        return (Good) context.createUnmarshaller()
                .unmarshal(new FileReader("C:\\Users\\segortseva\\" +
                        "IdeaProjects\\ozon\\src\\main\\java\\com\\example\\ozon\\goods.xml"));
    }



}
