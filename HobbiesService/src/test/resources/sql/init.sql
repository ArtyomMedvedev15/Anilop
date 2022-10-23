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