create table inventory
(
    id      bigserial
        primary key,
    created timestamp,
    status  varchar(255),
    updated timestamp
);

create table inventory_info
(
    id                bigint not null
        primary key,
    created           timestamp,
    hobby_id          bigint,
    serial_id         uuid,
    user_inventory_id bigint
);

insert into inventory values(777);
insert into inventory values(778);
insert into inventory values(779);

