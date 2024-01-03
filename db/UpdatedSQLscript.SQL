create table albums
(
    album_id int auto_increment
        primary key,
    name     varchar(255) not null,
    link     varchar(255) null
);

create table artists
(
    id   int          not null
        primary key,
    name varchar(255) null
);

create table genres
(
    genre_id int          not null
        primary key,
    type     varchar(255) not null
);

create table tracks
(
    track_id int auto_increment
        primary key,
    title    varchar(255) not null,
    artist   int          null,
    duration time         null,
    `rank`   int          null,
    genre    int          null,
    preview  varchar(255) not null,
    link     varchar(255) null,
    albums   int          null,
    constraint tracks_albums_fk
        foreign key (albums) references albums (album_id),
    constraint tracks_artists_id_fk
        foreign key (artist) references artists (id)
            on delete cascade,
    constraint tracks_genres_genre_id_fk
        foreign key (genre) references genres (genre_id)
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

create table playlist_track
(
    playlist_id int not null,
    track_id    int not null,
    primary key (playlist_id, track_id),
    constraint playlist_track_ibfk_1
        foreign key (playlist_id) references playlists (playlist_id),
    constraint playlist_track_ibfk_2
        foreign key (track_id) references tracks (track_id)
);

create index track_id
    on playlist_track (track_id);

