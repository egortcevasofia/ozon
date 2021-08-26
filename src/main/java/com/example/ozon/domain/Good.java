package com.example.ozon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne
    private User user;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private Category category;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "good", cascade = CascadeType.REMOVE)
    private Set<Image> foto;

}
