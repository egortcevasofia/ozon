package com.example.ozon.domain;

import com.example.ozon.enums.Gender;
import com.example.ozon.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
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


    @Column(name = "date_of_birthday")
    private LocalDate dateOfBirthday;


    @Column(name = "phone_number")
    private String phoneNumber;

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<BucketGood> bucketGoods;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Receipt> receipt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Shop shop;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Role> roles;


    public User(long id, @NotNull String email, @NotNull String firstName, @NotNull String lastName, @NotNull String password, @NotNull UserStatus userStatus, Set<Role> roles) {
        this.id = id;
        this.eMail = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userStatus = userStatus;
        this.roles = roles;
    }

    public User(long id, @Email @NotNull String eMail, @NotNull String password, Set<Role> roles) {
        this.id = id;
        this.eMail = eMail;
        this.password = password;
        this.roles = roles;
    }




}

