create table artists
(
    id         int auto_increment
        primary key,
    first_name varchar(255) not null,
    last_name  varchar(255) not null
);

create table tracks
(
    track_id int auto_increment
        primary key,
    title    varchar(255) not null,
    artist   int          not null,
    album    varchar(255) not null,
    duration time         not null,
    `rank`   int          not null,
    constraint tracks_artists_id_fk
        foreign key (artist) references artists (id)
);

create table users
(
    user_id    int auto_increment
        primary key,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    username   varchar(255) not null,
    password   varchar(255) not null,
    is_admin   tinyint(1)   not null,
    email      varchar(300) not null
);

