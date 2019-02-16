package app.repostitories;

import app.entities.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CatRepositoryImpl implements CatRepository {

    private final EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Cat> getAll() {
        entityManager.getTransaction().begin();

        List<Cat> cats = entityManager.createQuery("select c from Cat c", Cat.class)
                .getResultList();

        entityManager.getTransaction().commit();

        return cats;
    }


    @Override
    public Cat getObj(String str) {
        entityManager.getTransaction().begin();
        Cat cat = entityManager.createQuery("select c from Cat c where c.uuid = :uuid", Cat.class)
                .setParameter("uuid", str)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return cat;
    }

    @Override
    public void add(Cat obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }
}
