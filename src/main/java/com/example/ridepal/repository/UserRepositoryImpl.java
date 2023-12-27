package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            List<User> users = query.list();
            return users;
        }
    }

    @Override
    public List<User> getAllAdmins() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where isAdmin = :isAdmin", User.class);
            query.setParameter("isAdmin", true);
            List<User> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("User", "status", "admin");
            }
            return result;
        }
    }

    @Override
    public void createUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteUser(int id) {
        User userToDelete = getById(id);
        User deletedUser = getByUsername("deletedUser");
        int deletedUserId = deletedUser.getId();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            updatePlaylist(id, session, deletedUserId);

            session.remove(userToDelete);
            session.getTransaction().commit();
        }
    }


    private static void updatePlaylist(int id, Session session, int deletedUserId) {
        Query<Void> updatePlaylist = session.createNativeQuery(
                "UPDATE playlists SET user_id " +
                        "= :deletedUserId WHERE user_id = :userId");
        updatePlaylist.setParameter("deletedUserId", deletedUserId);
        updatePlaylist.setParameter("userId", id);
        updatePlaylist.executeUpdate();
    }


    @Override
    public User getByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where username = :username",
                    User.class);
            query.setParameter("username", username);
            List<User> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("User", "username", username);
            }
            return result.get(0);
        }
    }

    @Override
    public User getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            if (user == null) {
                throw new EntityNotFoundException("User", id);
            }
            return user;
        }
    }

    @Override
    public void modifyPermissions(User userToModify) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(userToModify);
            session.getTransaction().commit();
        }
    }
}
