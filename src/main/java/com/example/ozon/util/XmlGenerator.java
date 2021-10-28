package com.example.ozon.util;

import com.example.ozon.domain.Good;
import com.example.ozon.dto.ListOfGoodsDto;
import com.example.ozon.queue.AmqProducer;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.beans.Introspector;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/xml")
public class XmlGenerator {


    private AmqProducer amqProducer;

    @Autowired
    public void setAmqProducer(AmqProducer amqProducer) {
        this.amqProducer = amqProducer;
    }

    @GetMapping
    public void test() {

        val listOFGoods = new ListOfGoodsDto();
        val good = new Good();
        good.setName("name");
        good.setPrice(BigDecimal.TEN);
        good.setDescription("good good");
        List<Good> list = new ArrayList();
        list.add(good);
        list.add(good);
        listOFGoods.setListOfGoods(list);

        val test = toXmlString(listOFGoods, ListOfGoodsDto.class);
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
