package app.repositories.api;

import app.entities.JobApplication;
import app.repositories.GenericRepository;

public interface JobApplicationRepository extends GenericRepository<JobApplication, String> {
    void deleteJob(String id);
}
