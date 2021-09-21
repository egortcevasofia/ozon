package com.example.ozon.dto;

import com.example.ozon.domain.Category;
import com.example.ozon.domain.Image;
import com.example.ozon.domain.Shop;
import com.example.ozon.domain.User;
import com.example.ozon.enums.GoodStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodDto {

    private Long id;

    @NotNull
    private String name;

    private String description;

    private User user;

    private Shop shop;

    private Category category;

    @NotNull
    private int quantity;

    @NotNull
    private BigDecimal price;

    private Set<Image> foto;

    @NotNull
    private GoodStatus goodStatus;
}
