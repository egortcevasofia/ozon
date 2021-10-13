package com.example.ozon.domain;

import com.example.ozon.enums.GoodStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "goods")
@AllArgsConstructor
@RequiredArgsConstructor
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_id_seq")
    @SequenceGenerator(name = "goods_id_seq", sequenceName = "goods_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
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


    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", shop=" + shop +
                ", category=" + category +
                ", quantity=" + quantity +
                ", price=" + price +
                ", foto=" + foto +
                ", goodStatus=" + goodStatus +
                '}';
    }
}
