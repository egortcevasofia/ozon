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
@Table(name = "bucket_goods")
@AllArgsConstructor
@RequiredArgsConstructor
public class BucketGood {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bucket_goods_id_seq")
    @SequenceGenerator(name = "bucket_goods_id_seq", sequenceName = "bucket_goods_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private Good good;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private User user;

    public BucketGood(Good good, String name, int quantity, BigDecimal price, User user) {
        this.good = good;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
    }
}
