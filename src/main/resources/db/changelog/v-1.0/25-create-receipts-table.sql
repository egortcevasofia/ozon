create table receipts (
       id int8 not null,
        date_of_creation timestamp,
        user_id int8,
        primary key (id)
    )

GO

alter table receipts
       add constraint FK7t0uo7yxjck29e967rny84ky4
       foreign key (user_id)
       references users

GO