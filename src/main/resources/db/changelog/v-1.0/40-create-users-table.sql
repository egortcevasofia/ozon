    create table users (
       id int8 not null,
        avatar bytea,
        date_of_birthday date,
        delivery_address varchar(255),
        email varchar(255) not null,
        first_name varchar(255) not null,
        gender varchar(255),
        last_name varchar(255) not null,
        password varchar(255) not null,
        phone_number varchar(255),
        user_status varchar(255) not null,
        shop_id int8,
        primary key (id)
    )

GO
    alter table users
       add constraint FKbadc51v95q4hmcfflt8g3yfck
       foreign key (shop_id)
       references shops

GO