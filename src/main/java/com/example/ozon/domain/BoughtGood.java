package com.example.ozon.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "bought_goods")
@AllArgsConstructor
@RequiredArgsConstructor
public class BoughtGood {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bought_goods_id_seq")
    @SequenceGenerator(name = "bought_goods_id_seq", sequenceName = "bought_goods_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private Good good;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne
    @JsonManagedReference
    private Receipt receipt;

    public BoughtGood(Good good, String name, int quantity, BigDecimal price) {
        this.good = good;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
