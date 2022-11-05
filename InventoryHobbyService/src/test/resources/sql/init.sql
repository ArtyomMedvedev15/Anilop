create table inventory
(
    id      bigserial
        primary key,
    created timestamp,
    status  varchar(255),
    updated timestamp,
    user_id bigint
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
insert into inventory(id,user_id) values(1,777);
insert into inventory(id,user_id) values(2,778);
insert into inventory(id,user_id) values(3,779);

insert into inventory_info(id, created, hobby_id, serial_id, user_inventory_id) values (123,'2022-11-02',123,'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',777);
insert into inventory_info(id, created, hobby_id, serial_id, user_inventory_id) values (776,'2022-11-02',123,'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',778);
