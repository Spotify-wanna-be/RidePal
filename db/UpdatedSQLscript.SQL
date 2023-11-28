create table artists
(
    id         int auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

create table albums
(
    album_id  int auto_increment
        primary key,
    name      varchar(255) not null,
    artist_id int          not null,
    constraint albums_artists_id_fk
        foreign key (artist_id) references artists (id)
);

create table genres
(
    genre_id int auto_increment
        primary key,
    type     varchar(255) not null
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

create table playlists
(
    playlist_id int auto_increment
        primary key,
    name        varchar(255) not null,
    duration    time         not null,
    user_id     int          not null,
    `rank`      int          not null,
    constraint playlists_users_user_id_fk
        foreign key (user_id) references users (user_id)
            on update cascade on delete cascade
);

create table playlist_genre
(
    playlist_id int not null,
    genre_id    int null,
    constraint playlist_genre_genres_genre_id_fk
        foreign key (genre_id) references genres (genre_id),
    constraint playlist_genre_playlists_playlist_id_fk
        foreign key (playlist_id) references playlists (playlist_id)
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
    genre    int          not null,
    playlist int          not null,
    constraint tracks_artists_id_fk
        foreign key (artist) references artists (id)
            on delete cascade,
    constraint tracks_genres_genre_id_fk
        foreign key (genre) references genres (genre_id),
    constraint tracks_playlists_playlist_id_fk
        foreign key (playlist) references playlists (playlist_id)
);

