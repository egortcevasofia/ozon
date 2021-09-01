package com.example.ozon.domain;

import com.example.ozon.enums.ShopStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shops_id_seq")
    @SequenceGenerator(name = "shops_id_seq", sequenceName = "shops_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Good> goods;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "shop_status")
    private ShopStatus shopStatus;

}
