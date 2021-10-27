package com.example.ozon.controller;

import com.example.ozon.queue.AmqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private AmqProducer amqProducer;

    @Autowired
    public ShopController(AmqProducer amqProducer) {
        this.amqProducer = amqProducer;
    }

    @GetMapping(value = "/{shopId}/{numberOfOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getGoodsByOrder(@PathVariable(value = "shopId") Long shopId,
                                  @PathVariable(value = "numberOfOrder") Long numberOfOrder) {

        amqProducer.requestForOrder(shopId, numberOfOrder);
    }

}
