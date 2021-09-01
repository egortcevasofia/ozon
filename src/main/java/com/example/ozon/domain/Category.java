package com.example.ozon.domain;

import com.example.ozon.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Good> goods;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "category_status")
    private CategoryStatus categoryStatus;
}
