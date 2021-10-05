package com.example.ozon.builder;

import com.example.ozon.domain.*;
import com.example.ozon.enums.GoodStatus;

import java.math.BigDecimal;
import java.util.Set;

public class GoodBuilder {

    private Long id;

    private String name;

    private String description;

    private User user;

    private Shop shop;

    private Category category;

    private int quantity;

    private BigDecimal price;

    private Set<Image> foto;

    private GoodStatus goodStatus;


    public GoodBuilder buildId(long id) {
        this.id = id;
        return this;
    }

    public GoodBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public GoodBuilder buildDescription(String description) {
        this.description = description;
        return this;
    }

    public GoodBuilder buildUser(User user) {
        this.user = user;
        return this;
    }

    public GoodBuilder buildShop(Shop shop) {
        this.shop = shop;
        return this;
    }

    public GoodBuilder buildCategory(Category category) {
        this.category = category;
        return this;
    }

    public GoodBuilder buildQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public GoodBuilder buildPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public GoodBuilder buildImages(Set<Image> foto) {
        this.foto = foto;
        return this;
    }

    public GoodBuilder buildGoodStatus(GoodStatus goodStatus) {
        this.goodStatus = goodStatus;
        return this;
    }

    public Good build() {
        Good good = new Good();
        good.setId(id);
        good.setName(name);
        good.setDescription(description);
        good.setUser(user);
        good.setShop(shop);
        good.setCategory(category);
        good.setQuantity(quantity);
        good.setPrice(price);
        good.setFoto(foto);
        good.setGoodStatus(goodStatus);
        return good;
    }
}
