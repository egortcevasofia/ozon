package com.example.ozon.jsonExcenge;

import com.example.ozon.domain.Good;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class RequestClientServiceWebClientImpl implements RequestClientService {
    private WebClient webClient = WebClient.create("http://localhost:8081/");
    @Override
    public List<Good> getListOfGoods(Long shopId, Long numberOfOrder) {
        return webClient
                .get()
                .uri("/goods/")
                .retrieve()
                .bodyToFlux(Good.class)
                .collectList()
                .block();
    }
}
