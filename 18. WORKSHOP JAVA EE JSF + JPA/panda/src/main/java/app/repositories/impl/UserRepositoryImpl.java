package app.repositories.impl;

import app.entities.User;
import app.repositories.api.UserRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String SELECT_ALL_USERS = "select u from User u";
    private static final String SELECT_USER_BY_UUID = "select u from User u where u.uuid = :uuid";
    private static final String DELETE_USER_BY_UUID = "delete from User u where u.uuid = :uuid";
    private static final String SELECT_COUNT_OF_USERS = "select count(u) from User u";
    private static final String SELECT_USER_BY_USERNAME = "select u from User u where u.username = :username";
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "select u from User u where u.username = :username and u.password = :password";
    private static final String SELECT_ALL_WITH_ROLE_USER = "select u from User u where u.role = 'USER'";

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAll() {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery(SELECT_ALL_USERS, User.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return users;
    }

    @Override
    public User getByUUID(String uuid) {
        entityManager.getTransaction().begin();
        try {
            return entityManager
                    .createQuery(SELECT_USER_BY_UUID, User.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void save(User entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(User entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String uuid) {
        entityManager.getTransaction().begin();
        entityManager.createQuery(DELETE_USER_BY_UUID)
                .setParameter("uuid", uuid)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public long getNumberOfUsers() {
        entityManager.getTransaction().begin();
        long users = entityManager.createQuery(SELECT_COUNT_OF_USERS, Long.class)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return users;
    }

    @Override
    public User getByUsername(String username) {
        entityManager.getTransaction().begin();
        try {
            return entityManager.createQuery(SELECT_USER_BY_USERNAME, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        entityManager.getTransaction().begin();
        try {
            return entityManager.createQuery(SELECT_USER_BY_USERNAME_AND_PASSWORD, User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery(SELECT_ALL_WITH_ROLE_USER, User.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return users;
    }
}
