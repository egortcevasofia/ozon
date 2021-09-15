package com.example.ozon.dto;

import com.example.ozon.domain.BucketGood;
import com.example.ozon.domain.Receipt;
import com.example.ozon.domain.Role;
import com.example.ozon.domain.Shop;
import com.example.ozon.enums.Gender;
import com.example.ozon.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;

    @Email
    @NotNull
    private String eMail;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;

    private LocalDate dateOfBirthday;

    private String phoneNumber;

    private Gender gender;

    private byte[] avatar;

    private String deliveryAddress;

    @NotNull
    private UserStatus userStatus;

    private List<BucketGood> bucketGoods;

    private List<Receipt> receipt;

    private Shop shop;

    private Set<Role> roles;
}
