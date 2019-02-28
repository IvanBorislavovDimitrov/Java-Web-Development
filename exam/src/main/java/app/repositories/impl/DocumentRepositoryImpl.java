package app.repositories.impl;

import app.entities.Document;
import app.repositories.BaseRepository;
import app.repositories.api.DocumentRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class DocumentRepositoryImpl extends BaseRepository implements DocumentRepository {

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Document save(Document document) {
        return executeTransaction(entityManager -> {
            entityManager.persist(document);

            return document;
        });
    }

    @Override
    public Document getById(String id) {
        return executeTransaction(entityManager -> {
            try {
                return entityManager.createQuery("select d from Document d where d.id = :id", Document.class)
                        .setParameter("id", id)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        });
    }

    @Override
    public List<Document> getAll() {
        return executeTransaction(entityManager -> entityManager.createQuery("select d from Document d", Document.class)
                .getResultList());
    }

    @Override
    public void deleteById(String id) {
        executeTransaction(entityManager -> entityManager.createQuery("delete from Document d where d.id = :id")
                .setParameter("id", id)
                .executeUpdate());
    }
}
