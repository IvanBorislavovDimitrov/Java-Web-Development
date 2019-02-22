package app.repositories.impl;

import app.entities.Package;
import app.enums.Status;
import app.repositories.api.PackageRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class PackageRepositoryImpl implements PackageRepository {

    private final EntityManager entityManager;

    private static final String SELECT_ALL_PACKAGES = "select p from Package p";
    private static final String SELECT_PACKAGE_BY_UUID = "select p from Package p where p.uuid = :uuid";
    private static final String DELETE_PACKAGE_BY_UUID = "delete from Package p where p.uuid = :uuid";
    private static final String SELECT_ALL_BY_STATUS = "select p from Package p join p.recipient r where p.status = :status";
    private static final String UPDATE_PACKAGE_TO_SHIPPED = "update Package p set p.status = 'SHIPPED' where p.uuid = :uuid";
    private static final String UPDATE_PACKAGE_TO_DELIVERED = "update Package p set p.status = 'DELIVERED' where p.uuid = :uuid";

    @Inject
    public PackageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Package> getAll() {
        entityManager.getTransaction().begin();
        List<Package> packages = entityManager.createQuery(SELECT_ALL_PACKAGES, Package.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return packages;
    }

    @Override
    public Package getByUUID(String uuid) {
        entityManager.getTransaction().begin();
        try {
            return entityManager.createQuery(SELECT_PACKAGE_BY_UUID, Package.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void save(Package entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Package entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String uuid) {
        entityManager.getTransaction().begin();
        entityManager.createQuery(DELETE_PACKAGE_BY_UUID)
                .setParameter("uuid", uuid)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Package> getAllWithStatusPending() {
        entityManager.getTransaction().begin();
        List<Package> packages = entityManager.createQuery(SELECT_ALL_BY_STATUS, Package.class)
                .setParameter("status", Status.PENDING)
                .getResultList();
        entityManager.getTransaction().commit();

        return packages;
    }

    @Override
    public List<Package> getAllWithStatusShipped() {
        entityManager.getTransaction().begin();
        List<Package> packages = entityManager.createQuery(SELECT_ALL_BY_STATUS, Package.class)
                .setParameter("status", Status.SHIPPED)
                .getResultList();
        entityManager.getTransaction().commit();

        return packages;
    }

    @Override
    public List<Package> getAllWithStatusDelivered() {
        entityManager.getTransaction().begin();
        List<Package> packages = entityManager.createQuery(SELECT_ALL_BY_STATUS, Package.class)
                .setParameter("status", Status.DELIVERED)
                .getResultList();
        entityManager.getTransaction().commit();

        return packages;
    }

    @Override
    public void shipPackage(String uuid) {
        entityManager.getTransaction().begin();
        entityManager.createQuery(UPDATE_PACKAGE_TO_SHIPPED)
                .setParameter("uuid", uuid)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deliverPackage(String uuid) {
        entityManager.getTransaction().begin();
        entityManager.createQuery(UPDATE_PACKAGE_TO_DELIVERED)
                .setParameter("uuid", uuid)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Package aPackage) {
        entityManager.getTransaction().begin();
        entityManager.refresh(aPackage);
        entityManager.getTransaction().commit();
    }

}
