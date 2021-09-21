    create table goods (
       id int8 not null,
        description varchar(255),
        good_status varchar(255) not null,
        name varchar(255),
        price numeric(19, 2),
        quantity int4,
        category_id int8,
        shop_id int8,
        user_id int8,
        primary key (id)
    )

GO

    alter table goods
       add constraint FKm164hdre8y3lew7lvtu0sneqe
       foreign key (category_id)
       references categories

GO

    alter table goods
       add constraint FK1l718am7r7fapps3aaei5buur
       foreign key (shop_id)
       references shops

GO

    alter table goods
       add constraint FKovmhvi2lnpxl8r3mjrysrs197
       foreign key (user_id)
       references users

GO