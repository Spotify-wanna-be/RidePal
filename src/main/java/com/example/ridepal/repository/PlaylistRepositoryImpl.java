package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlaylistRepositoryImpl implements PlaylistRepository {
    private final SessionFactory sessionFactory;


    @Autowired
    public PlaylistRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Playlist> getAll(){
        try (Session session = sessionFactory.openSession()) {
            Query<Playlist> query = session.createQuery("from Playlist ", Playlist.class);
            List<Playlist> playlists = query.list();
            return playlists;
        }
    }

    @Override
    public List<Playlist> getAllCount() {
        try (Session session = sessionFactory.openSession()) {
            Query<Playlist> query = session.createQuery("from Playlist ", Playlist.class);
            return query.list();
        }
    }

    public List<Playlist> getUsersPlaylists(User user){
        try (Session session = sessionFactory.openSession()) {
            Query<Playlist> query = session.createQuery("FROM Playlist WHERE createdBy = :user", Playlist.class);
            query.setParameter("user", user);
            List<Playlist> playlists = query.list();
            return playlists;
        }
    }

    @Override
    public List<Playlist> getHighestRank(){
        try (Session session = sessionFactory.openSession()) {
            Query<Playlist> query = session.createQuery("FROM Playlist ORDER BY rank DESC", Playlist.class);
            query.setMaxResults(3);
            return query.list();
        }
    }

    @Override
    public List<Playlist> get(PlaylistFilterOptions playlistFilterOptions) {
        try (Session session = sessionFactory.openSession()) {
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            playlistFilterOptions.getName().ifPresent(value -> {
                filters.add(" name like :name ");
                params.put("name", String.format("%%%s%%", value));
            });

            playlistFilterOptions.getDuration().ifPresent(value -> {
                filters.add(" duration like :duration ");
                params.put("duration", String.format("%%%s%%", value));
            });

            playlistFilterOptions.getGenre().ifPresent(value -> {
                filters.add(" genre.type like :genre ");
                params.put("genre", String.format("%%%s%%", value));
            });

            StringBuilder queryString = new StringBuilder("from Playlist ");
            if (!filters.isEmpty()) {
                queryString.append(" where ")
                        .append(String.join(" and ", filters));
            }


            queryString.append(generateOrderBy(playlistFilterOptions));
            Query<Playlist> query = session.createQuery(queryString.toString(), Playlist.class);
            query.setProperties(params);
            return query.list();
        }
    }

    private String generateOrderBy(PlaylistFilterOptions playlistFilterOptions) {
        if (playlistFilterOptions.getSortBy().isEmpty()) {
            return "";
        }
        String orderBy = "";
        switch (playlistFilterOptions.getSortBy().get()) {
            case "name":
                orderBy = "name";
                break;
            case "duration":
                orderBy = "duration";
                break;
            case "genres":
                orderBy = "genres.type";
                break;
            default:
                return "";
        }
        orderBy = String.format(" order by %s", orderBy);
        if (playlistFilterOptions.getSortOrder().isPresent()
                && playlistFilterOptions.getSortOrder().get().equalsIgnoreCase("desc")) {
            orderBy = String.format("%s desc", orderBy);
        }
        return orderBy;
    }

    @Override
    public Playlist getByPlaylistId(int id) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            Playlist playlist = session.get(Playlist.class, id);
            if (playlist == null) {
                throw new EntityNotFoundException("Playlist", id);
            }
            return playlist;
        }
    }


    @Override
    public void create(Playlist playlist) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(playlist);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Playlist playlist) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(playlist);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int playlistId) {
        Playlist playlistToDelete = getByPlaylistId(playlistId);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(playlistToDelete);
            session.getTransaction().commit();
        }
    }
}
