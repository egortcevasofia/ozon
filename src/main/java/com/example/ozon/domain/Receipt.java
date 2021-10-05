package com.example.ozon.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "receipts")
@AllArgsConstructor
@RequiredArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipts_id_seq")
    @SequenceGenerator(name = "receipts_id_seq", sequenceName = "receipts_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JsonManagedReference
    private User user;

    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @OneToMany(mappedBy = "receipt")
    private List<BoughtGood> boughtGoods;

    public Receipt(User user, LocalDateTime dateOfCreation, List<BoughtGood> boughtGoods) {
        this.user = user;
        this.dateOfCreation = dateOfCreation;
        this.boughtGoods = boughtGoods;
    }
}
