package com.example.ozon.queue;

import com.example.ozon.mapper.XmlGoodMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import generated.Good;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static org.jsoup.Jsoup.parse;
import static org.jsoup.parser.Parser.xmlParser;

@Slf4j
@Component
public class AmqReceiver {

    private XmlGoodMapper xmlGoodMapper;

    @Autowired
    public void setXmlGoodMapper(XmlGoodMapper xmlGoodMapper) {
        this.xmlGoodMapper = xmlGoodMapper;
    }

    @JmsListener(destination = "${inbound.queue.name}")
    public void aue(String message) {

        val xml = parse(message, " ", xmlParser());

        val stringXml = xml.outerHtml();
        val xmlMapper = new XmlMapper();
        Good good = null;
        try {
            good = xmlMapper.readValue(stringXml, Good.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't parse response from Onyx", e);
        }

        com.example.ozon.domain.Good domainGood = xmlGoodMapper.xmlGoodToGood(good);
        log.info(domainGood.toString());
    }
}


/*
<good>
    <description>good good</description>
    <name>name</name>
    <price>10</price>
    <quantity>0</quantity>
</good>
* */