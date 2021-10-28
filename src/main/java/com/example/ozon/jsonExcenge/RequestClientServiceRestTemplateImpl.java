package com.example.ozon.jsonExcenge;

import com.example.ozon.dto.ShopRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class RequestClientServiceRestTemplateImpl implements RequestClientService{

    RestTemplate restTemplate = new RestTemplate();
    String uri = "http://localhost:8081/goods";

    @Override
    public ArrayList getListOfGoods(Long shopId,Long numberOfOrder) {
       return restTemplate.getForObject(uri, ArrayList.class);
    }
}
