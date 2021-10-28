package com.example.ozon.jsonExcenge;

import com.example.ozon.dto.GoodDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "ozon2client", url = "http://localhost:8081/")
public interface Ozon2FeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/goods", consumes = "application/json")
    List<GoodDto> getListOfGoods();

}
