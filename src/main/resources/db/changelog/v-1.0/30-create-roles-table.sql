 create table roles (
       id int8 not null,
        name varchar(255),
        primary key (id)
    )

GO

    alter table roles
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name)

GO