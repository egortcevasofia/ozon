package com.example.ozon.domain;

import com.example.ozon.enums.GoodStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "goods")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne
    private Category category;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "good", cascade = CascadeType.REMOVE)
    private Set<Image> foto;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "good_status")
    private GoodStatus goodStatus;


}
