package com.example.ozon.dto;

import com.example.ozon.domain.Good;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ListOfGoodsDto {

    private List<Good> listOfGoods;
}
