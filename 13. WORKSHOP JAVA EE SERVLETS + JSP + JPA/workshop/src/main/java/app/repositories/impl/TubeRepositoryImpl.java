package app.repositories.impl;

import app.entities.Tube;
import app.enums.TubeStatus;
import app.repositories.api.TubeRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static app.constants.Constants.PERSISTENCE_UNIT;

public class TubeRepositoryImpl implements TubeRepository {

    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT)
                .createEntityManager();
    }

    @Override
    public List<Tube> getAll() {
        entityManager.getTransaction().begin();

        CriteriaQuery<Tube> userCriteriaQuery = entityManager.getCriteriaBuilder()
                .createQuery(Tube.class);

        userCriteriaQuery.from(Tube.class);

        List<Tube> tubes = entityManager.createQuery(userCriteriaQuery).getResultList();
        entityManager.getTransaction().commit();

        return tubes;
    }

    @Override
    public Tube getObj(String str) {
        entityManager.getTransaction().begin();
        Tube tube = null;
        try {
            tube = entityManager
                    .createQuery("select t from Tube t where t.uuid = :uuid", Tube.class)
                    .setParameter("uuid", str)
                    .getSingleResult();

            entityManager.refresh(tube);
        } catch (NoResultException e) {
            // No action required
        }

        entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public void persist(Tube tube) {
        entityManager.getTransaction().begin();
        entityManager.merge(tube);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Tube> getAllByUsername(String username) {
        entityManager.getTransaction().begin();
        List<Tube> tubes = entityManager
                .createQuery("select t from Tube t join t.uploader as u where u.username = :username", Tube.class)
                .setParameter("username", username)
                .getResultList();

        entityManager.getTransaction().commit();

        return tubes;
    }

    @Override
    public void addView(String tubeUuid) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Tube as t set t.views = t.views + 1 where t.uuid = :uuid")
                .setParameter("uuid", tubeUuid)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Tube> getAllPendingTubes() {
        entityManager.getTransaction().begin();
        List<Tube> pendingTubes = entityManager.createQuery("select t from Tube t where t.tubeStatus = 'PENDING'", Tube.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return pendingTubes;
    }

    @Override
    public List<Tube> getAllApprovedTubes() {
        entityManager.getTransaction().begin();
        List<Tube> approvedTubes = entityManager.createQuery("select t from Tube t where t.tubeStatus = 'APPROVED'", Tube.class)
                .getResultList();
        entityManager.getTransaction().commit();

        return approvedTubes;
    }

    @Override
    public void setTubeStatus(String uuid, TubeStatus tubeStatus) {
        if (getObj(uuid) == null) {
            return;
        }
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Tube t set t.tubeStatus = :tubeStatus where t.uuid = :uuid")
                .setParameter("tubeStatus", tubeStatus)
                .setParameter("uuid", uuid)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
