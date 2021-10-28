package com.example.ozon.jsonExcenge;

import com.example.ozon.domain.Good;

import java.util.List;

public interface RequestClientService {
    List<Good> getListOfGoods(Long shopId, Long numberOfOrder);
}
