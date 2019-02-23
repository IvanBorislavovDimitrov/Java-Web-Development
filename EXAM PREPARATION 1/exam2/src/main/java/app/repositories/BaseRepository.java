package app.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.function.Function;

public abstract class BaseRepository {

    private final EntityManager entityManager;

    @Inject
    protected BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected <T> T executeTransaction(Function<EntityManager, T> function) {
        try {
            entityManager.getTransaction().begin();

            return function.apply(entityManager);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.getTransaction().commit();
        }

        return null;
    }
}
