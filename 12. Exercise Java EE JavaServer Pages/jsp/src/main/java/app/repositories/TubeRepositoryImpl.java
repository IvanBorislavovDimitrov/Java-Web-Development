package app.repositories;

import app.dtos.TubeServiceDto;
import app.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;

public class TubeRepositoryImpl implements TubeRepository {

    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        entityManager = Persistence.createEntityManagerFactory("tubes").createEntityManager();
    }

    @Override
    public List<Tube> getAll() {
        return entityManager.createQuery("select t from Tube t", Tube.class).getResultList();
    }

    @Override
    public Tube getObj(String val) {
        return entityManager.createQuery("select t from Tube t where t.name = :name", Tube.class)
                .setParameter("name", val)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public void save(Tube obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }
}
