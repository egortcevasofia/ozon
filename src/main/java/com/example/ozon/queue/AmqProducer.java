package com.example.ozon.queue;

import generated.ShopRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Component
@Slf4j
public class AmqProducer {

    @Value("${request.for.ozon2}")
    private String outboundQueue;

    private JmsTemplate jmsTemplate;

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Boolean requestForOrder(Long shopId, Long numberOfOrder){

        ShopRequest shopRequest = new ShopRequest();
        shopRequest.setShopId(shopId);
        shopRequest.setNumberOfOrder(numberOfOrder);



        try {
            JAXBContext context = JAXBContext.newInstance(ShopRequest.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            mar.marshal(shopRequest, sw);
            String xmlString = sw.toString();

            log.info("stroca {}", xmlString);


            jmsTemplate.convertAndSend(this.outboundQueue, xmlString);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return true;
    }
}
