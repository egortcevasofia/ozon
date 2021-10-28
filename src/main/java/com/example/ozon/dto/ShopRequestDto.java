package com.example.ozon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
@Setter
@ToString
public class ShopRequestDto {

    private long shopId;
    private long numberOfOrder;

}
