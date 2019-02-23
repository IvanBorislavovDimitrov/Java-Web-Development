package app.service.api;

import app.model.service.JobApplicationServiceModel;

import java.io.Serializable;
import java.util.List;

public interface JobApplicationService extends Serializable {

    JobApplicationServiceModel save(JobApplicationServiceModel jobApplicationServiceModel);

    JobApplicationServiceModel getById(String id);

    List<JobApplicationServiceModel> getAll();

    void deleteJob(String id);
}
