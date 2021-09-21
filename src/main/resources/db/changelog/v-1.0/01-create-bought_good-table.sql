create table bought_goods (
       id int8 not null,
        name varchar(255),
        price numeric(19, 2),
        quantity int4,
        good_id int8,
        receipt_id int8,
        primary key (id)
    )

GO

    alter table bought_goods
       add constraint FK3a94audu1ujw27wflw0y7778m
       foreign key (good_id)
       references goods

GO

    alter table bought_goods
       add constraint FKb44r5xvtv4lrrc3oesl82q9pt
       foreign key (receipt_id)
       references receipts

GO
