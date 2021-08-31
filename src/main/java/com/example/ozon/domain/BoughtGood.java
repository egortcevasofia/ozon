package com.example.ozon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bought_goods")
public class BoughtGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Good good;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne
    private Receipt receipt;

    @Column(name = "price")
    private BigDecimal price;
}
