    create table bucket_goods (
       id int8 not null,
        name varchar(255),
        price numeric(19, 2),
        quantity int4,
        good_id int8,
        user_id int8,
        primary key (id)
    )

GO

alter table bucket_goods
       add constraint FKt89n5vyk2i6asqnss5624f1ww
       foreign key (good_id)
       references goods

GO

    alter table bucket_goods
       add constraint FKg4iu1cvl0vweocbbkbwy74b7j
       foreign key (user_id)
       references users
GO