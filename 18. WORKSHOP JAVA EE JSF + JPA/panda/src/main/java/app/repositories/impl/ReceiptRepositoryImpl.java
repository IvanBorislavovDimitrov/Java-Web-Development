package app.repositories.impl;

import app.entities.Receipt;
import app.repositories.api.ReceiptRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class ReceiptRepositoryImpl implements ReceiptRepository {

    private static final String SELECT_ALL_RECEIPTS = "select r from Receipt r";
    private static final String SELECT_RECEIPT_BY_UUID = "select r from Receipt r where r.uuid = :uuid";
    private static final String DELETE_RECEIPT_BY_UUID = "delete from Receipt r where r.uuid = :uuid";
    private static final String SELECT_ALL_BY_RECIPIENT = "select r from Receipt r join r.recipient u where u.username = :recipientName";
    private static final String SELECT_BY_USERNAME_AND_UUID = "select r from Receipt r join r.recipient u where u.username = :username and r.uuid = :uuid";

    private final EntityManager entityManager;

    @Inject
    public ReceiptRepositoryImpl(EntityManager entityManagerl) {
        this.entityManager = entityManagerl;
    }

    @Override
    public List<Receipt> getAll() {
        entityManager.getTransaction().begin();
        List<Receipt> receipts = entityManager.createQuery(SELECT_ALL_RECEIPTS, Receipt.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return receipts;
    }

    @Override
    public Receipt getByUUID(String uuid) {
        entityManager.getTransaction().begin();
        try {
            return entityManager.createQuery(SELECT_RECEIPT_BY_UUID, Receipt.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void save(Receipt entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Receipt entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String uuid) {
        entityManager.getTransaction().begin();
        entityManager.createQuery(DELETE_RECEIPT_BY_UUID)
                .setParameter("uuid", uuid)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Receipt> getAllByRecipient(String recipient) {
        entityManager.getTransaction().begin();
        List<Receipt> receipts = entityManager.createQuery(SELECT_ALL_BY_RECIPIENT, Receipt.class)
                .setParameter("recipientName", recipient)
                .getResultList();
        entityManager.getTransaction().commit();

        return receipts;
    }

    @Override
    public Receipt getByUsernameAndUUID(String username, String uuid) {
        entityManager.getTransaction().begin();
        try {
            return entityManager.createQuery(SELECT_BY_USERNAME_AND_UUID, Receipt.class)
                    .setParameter("username", username)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } finally {
            entityManager.getTransaction().commit();
        }
    }
}
