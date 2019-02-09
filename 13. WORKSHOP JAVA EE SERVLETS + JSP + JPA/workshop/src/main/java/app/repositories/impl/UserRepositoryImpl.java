package app.repositories.impl;

import app.entities.User;
import app.enums.UserRole;
import app.repositories.api.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static app.constants.Constants.PERSISTENCE_UNIT;

public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;

    public UserRepositoryImpl() {
        entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT)
                .createEntityManager();
    }

    @Override
    public List<User> getAll() {
        entityManager.getTransaction().begin();

        CriteriaQuery<User> userCriteriaQuery = entityManager.getCriteriaBuilder()
                .createQuery(User.class);

        userCriteriaQuery.from(User.class);

        List<User> users = entityManager.createQuery(userCriteriaQuery).getResultList();
        entityManager.getTransaction().commit();

        return users;
    }

    @Override
    public User getObj(String username) {
        entityManager.getTransaction().begin();

        User user = entityManager
                .createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();

        entityManager.getTransaction().commit();

        return user;
    }

    @Override
    public void persist(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public boolean doesUserExist(String username, String password) {
        entityManager.getTransaction().begin();
        try {
            entityManager
                    .createQuery("select u from User u where u.username = :username and u.password = :password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public boolean doesEmailExist(String email) {
        entityManager.getTransaction().begin();

        try {
            entityManager.createQuery("select u from User u where u.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();

            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public boolean doesUsernameExist(String username) {
        entityManager.getTransaction().begin();

        try {
            entityManager.createQuery("select u from User u where u.username = :username")
                    .setParameter("username", username)
                    .getSingleResult();

            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public long usersCount() {
        entityManager.getTransaction().begin();
        long count = entityManager.createQuery("select count (u) from User u", Long.class).getSingleResult();
        entityManager.getTransaction().commit();

        return count;
    }

    @Override
    public UserRole getUserRole(String username) {
        entityManager.getTransaction().begin();
        UserRole userRole = entityManager
                .createQuery("select u.userRole from User u where u.username = :username", UserRole.class)
                .setParameter("username", username)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return userRole;
    }
}
