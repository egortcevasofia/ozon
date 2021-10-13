package com.example.ozon.util;

import com.example.ozon.domain.Good;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.beans.Introspector;
import java.io.StringWriter;
import java.math.BigDecimal;

@RestController
@Slf4j
@RequestMapping("/xml")
public class XmlGenerator {

    @GetMapping
    public void test() {

        val good = new Good();
        good.setName("name");
        good.setPrice(BigDecimal.TEN);
        good.setDescription("good good");

        val test = toXmlString(good, Good.class);
        log.info(test);
    }
    public static <T> String toXmlString(T o, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // remove xml prolog
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // formatted output

            QName name = new QName(Introspector.decapitalize(clazz.getSimpleName()));
            JAXBElement jaxbElement = new JAXBElement<>(name, clazz, o);

            StringWriter sw = new StringWriter();
            marshaller.marshal(jaxbElement, sw);
            return sw.toString();

        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }
}
