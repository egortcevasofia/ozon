    create table images (
       id int8 not null,
        image bytea,
        good_id int8,
        primary key (id)
    )

GO

alter table images
       add constraint FK40fx84ymgmg7oryovw7m30vdg
       foreign key (good_id)
       references goods

GO