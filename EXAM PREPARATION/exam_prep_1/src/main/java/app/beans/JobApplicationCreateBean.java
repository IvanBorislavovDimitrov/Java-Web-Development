package app.beans;

import app.model.binding.JobApplicationBindingModel;
import app.model.service.JobApplicationServiceModel;
import app.model.view.JobApplicationViewModel;
import app.service.api.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class JobApplicationCreateBean {

    private JobApplicationBindingModel jobApplicationBindingModel;

    @Inject
    private JobApplicationService jobApplicationService;

    @Inject
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        jobApplicationBindingModel = new JobApplicationBindingModel();
    }

    public void addJobApplication() throws IOException {
        jobApplicationService.save(modelMapper.map(jobApplicationBindingModel, JobApplicationServiceModel.class));
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("/");
    }

    public List<JobApplicationViewModel> allJobApplications() {
        return jobApplicationService.getAll().stream()
                .map(jobApplicationServiceModel -> modelMapper.map(jobApplicationServiceModel, JobApplicationViewModel.class))
                .collect(Collectors.toList());
    }

    public void setJobApplicationBindingModel(JobApplicationBindingModel jobApplicationBindingModel) {
        this.jobApplicationBindingModel = jobApplicationBindingModel;
    }

    public JobApplicationBindingModel getJobApplicationBindingModel() {
        return jobApplicationBindingModel;
    }
}
