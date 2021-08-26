package com.example.ozon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "filename")
    private String filename;

    @ManyToOne
    private Good good;

}

