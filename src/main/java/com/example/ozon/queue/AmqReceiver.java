package com.example.ozon.queue;

import com.example.ozon.domain.Good;
import com.example.ozon.mapper.XmlGoodMapper;
import com.example.ozon.service.GoodService;
import generated.ListOfGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AmqReceiver {

    private XmlGoodMapper xmlGoodMapper;
    private GoodService goodService;

    @Autowired
    public AmqReceiver(XmlGoodMapper xmlGoodMapper, GoodService goodService) {
        this.xmlGoodMapper = xmlGoodMapper;
        this.goodService = goodService;
    }

    @JmsListener(destination = "${response.from.ozon2}")
    public void getAndSaveListOfGoods(String message) {

        log.info("get message {}", message);


        try {
            List<ListOfGoods.Good> listOfGeneratedGoods = unmarshall(message).getGood();
            List<Good> listOfDomainGoods = listOfGeneratedGoods
                    .stream()
                    .map(good -> xmlGoodMapper.xmlGoodToGood(good))
                    .collect(Collectors.toList());


            log.info(listOfDomainGoods.toString());

            goodService.saveAll(listOfDomainGoods);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private ListOfGoods unmarshall(String message) throws Exception {

        JAXBContext context = JAXBContext.newInstance(ListOfGoods.class);
        return (ListOfGoods) context.createUnmarshaller()
                .unmarshal(new StringReader(message));

    }

}
