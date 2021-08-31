package com.example.ozon.domain;

import com.example.ozon.enums.Gender;
import com.example.ozon.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    @NotNull
    @Column(name = "email")
    private String eMail;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "date_of_birthday")
    private LocalDate dateOfBirthday;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user")
    private List<BucketGood> bucketGoods;

    @OneToMany(mappedBy = "user")
    private List<Receipt> receipt;

    @ManyToOne
    private Shop shop;

    @ManyToMany
    private Set<Role> roles;

}
