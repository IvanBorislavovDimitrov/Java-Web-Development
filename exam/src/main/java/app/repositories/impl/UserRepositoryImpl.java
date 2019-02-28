package app.repositories.impl;

import app.entities.User;
import app.repositories.BaseRepository;
import app.repositories.api.UserRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {

    @Inject
    protected UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User save(User obj) {
        return executeTransaction(entityManager -> {
            entityManager.persist(obj);
            return obj;
        });
    }

    @Override
    public User getById(String id) {
        return executeTransaction(entityManager -> {
            try {
                return entityManager.createQuery("select u from User u where u.id = :id", User.class)
                        .setParameter("id", id)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        });
    }

    @Override
    public List<User> getAll() {
        return executeTransaction(entityManager -> entityManager.createQuery("select u from User u", User.class)
                .getResultList());
    }

    @Override
    public boolean isUserPresented(String username, String password) {
        return executeTransaction(entityManager -> {
            try {
                entityManager.createQuery("select u from User u where u.username = :username and u.password = :password")
                        .setParameter("username", username)
                        .setParameter("password", password)
                        .getSingleResult();
            } catch (NoResultException e) {
                return false;
            }

            return true;
        });
    }

    @Override
    public User getByUsername(String username) {
        return executeTransaction(entityManager -> {
            try {
                return entityManager.createQuery("select u from User u where u.username = :username", User.class)
                        .setParameter("username", username)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        });
    }

    @Override
    public void refresh(User user) {
        executeTransaction(entityManager -> {
            entityManager.refresh(user);

            return user;
        });
    }
}
