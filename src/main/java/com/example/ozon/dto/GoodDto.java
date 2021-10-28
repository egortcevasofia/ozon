package com.example.ozon.dto;

import com.example.ozon.domain.Category;
import com.example.ozon.domain.Image;
import com.example.ozon.domain.Shop;
import com.example.ozon.domain.User;
import com.example.ozon.enums.GoodStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodDto implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private Shop shop;
    @JsonIgnore
    private Category category;

    @NotNull
    private int quantity;

    @NotNull
    private BigDecimal price;
    @JsonIgnore
    private Set<Image> foto;

    @NotNull
    private GoodStatus goodStatus;
}
