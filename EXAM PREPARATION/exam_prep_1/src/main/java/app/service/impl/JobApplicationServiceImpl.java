package app.service.impl;

import app.entities.JobApplication;
import app.model.service.JobApplicationServiceModel;
import app.repositories.api.JobApplicationRepository;
import app.service.api.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JobApplicationServiceModel save(JobApplicationServiceModel jobApplicationServiceModel) {
        return modelMapper.map(jobApplicationRepository
                .save(modelMapper.map(jobApplicationServiceModel, JobApplication.class)), JobApplicationServiceModel.class);
    }

    @Override
    public JobApplicationServiceModel getById(String id) {
        return modelMapper.map(jobApplicationRepository.getById(id), JobApplicationServiceModel.class);
    }

    @Override
    public List<JobApplicationServiceModel> getAll() {
        return jobApplicationRepository.getAll().stream()
                .map(jobApplication -> modelMapper.map(jobApplication, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteJob(String id) {
        jobApplicationRepository.deleteJob(id);
    }
}
