package com.example.ozon.criterias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodSearchCriteria {
    private String name;

    private long shopId;

    private long categoryId;

    private BigDecimal priceMin = new BigDecimal(0.0);

    private BigDecimal priceMax = new BigDecimal(Long.MAX_VALUE);

    private Boolean hasFoto = false;

}
