create table hobby
(
    id        bigserial
        primary key,
    created   timestamp,
    status    varchar(255),
    updated   timestamp,
    author_id bigint,
    describe  varchar(255),
    duration  timestamp,
    logo_path varchar(255),
    name      varchar(255),
    rating    integer default 0,
    type      integer
);

insert into hobby values(7777,'2022-09-22','CREATED','2022-09-22',1,'Temp','2022-09-22','Temp','Temp',2,0);