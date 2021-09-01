package com.example.ozon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "bought_goods")
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
    private Receipt receipt;


}
