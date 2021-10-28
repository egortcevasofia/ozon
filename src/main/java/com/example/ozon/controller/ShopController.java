package com.example.ozon.controller;

import com.example.ozon.domain.Good;
import com.example.ozon.dto.GoodDto;
import com.example.ozon.jsonExcenge.Ozon2FeignClient;
import com.example.ozon.jsonExcenge.RequestClientService;
import com.example.ozon.queue.AmqProducer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private AmqProducer amqProducer;
    private RequestClientService requestClientService;
    private Ozon2FeignClient ozon2FeignClient;

    public ShopController(AmqProducer amqProducer, RequestClientService requestClientService, Ozon2FeignClient ozon2FeignClient) {
        this.amqProducer = amqProducer;
        this.requestClientService = requestClientService;
        this.ozon2FeignClient = ozon2FeignClient;
    }

    @GetMapping(value = "/{shopId}/{numberOfOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getGoodsByOrderByXml(@PathVariable(value = "shopId") Long shopId,
                                  @PathVariable(value = "numberOfOrder") Long numberOfOrder) {

        amqProducer.requestForOrder(shopId, numberOfOrder);
    }


    @GetMapping(value = "json/{shopId}/{numberOfOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Good> getGoodsByOrderByJson(@PathVariable(value = "shopId") Long shopId,
                                            @PathVariable(value = "numberOfOrder") Long numberOfOrder) {

        return requestClientService.getListOfGoods(shopId, numberOfOrder);
    }

    @GetMapping(value = "feign/{shopId}/{numberOfOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodDto> getGoodsByOrderByFeign(@PathVariable(value = "shopId") Long shopId,
                                                 @PathVariable(value = "numberOfOrder") Long numberOfOrder) {

        return ozon2FeignClient.getListOfGoods();
    }




}
