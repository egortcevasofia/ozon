    create table users_roles (
       users_id int8 not null,
        roles_id int8 not null,
        primary key (users_id, roles_id)
    )


GO
    alter table users_roles
       add constraint FKa62j07k5mhgifpp955h37ponj
       foreign key (roles_id)
       references roles
GO

    alter table users_roles
       add constraint FKml90kef4w2jy7oxyqv742tsfc
       foreign key (users_id)
       references users

GO