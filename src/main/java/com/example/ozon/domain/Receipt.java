package com.example.ozon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipts_id_seq")
    @SequenceGenerator(name = "receipts_id_seq", sequenceName = "receipts_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @OneToMany(mappedBy = "receipt")
    private List<BoughtGood> boughtGoods;

}
