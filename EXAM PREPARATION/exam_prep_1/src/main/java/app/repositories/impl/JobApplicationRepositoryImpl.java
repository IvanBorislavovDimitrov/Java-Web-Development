package app.repositories.impl;

import app.entities.JobApplication;
import app.repositories.BaseRepository;
import app.repositories.api.JobApplicationRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class JobApplicationRepositoryImpl extends BaseRepository implements JobApplicationRepository {

    @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public JobApplication save(JobApplication jobApplication) {
        return executeTransaction(entityManager -> {
            entityManager.persist(jobApplication);
            return jobApplication;
        });
    }

    @Override
    public JobApplication getById(String id) {
        return executeTransaction(entityManager -> {
            try {
                return entityManager.createQuery("select j from JobApplication j where j.id = :id", JobApplication.class)
                        .setParameter("id", id)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        });
    }

    @Override
    public List<JobApplication> getAll() {
        return executeTransaction(entityManager -> entityManager.createQuery("select j from JobApplication j", JobApplication.class)
                .getResultList());
    }

    @Override
    public void deleteJob(String id) {
        executeTransaction(entityManager -> entityManager.createQuery("delete from JobApplication j where j.id = :id")
                .setParameter("id", id)
                .executeUpdate());
    }
}
