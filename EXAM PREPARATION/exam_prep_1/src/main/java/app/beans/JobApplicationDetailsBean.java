package app.beans;

import app.model.view.JobApplicationViewModel;
import app.service.api.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;

@Named
@RequestScoped
public class JobApplicationDetailsBean {

    private JobApplicationViewModel jobApplicationViewModel;

    @Inject
    private JobApplicationService jobApplicationService;

    @Inject
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        jobApplicationViewModel = createJobApplicationViewModel();
    }

    private JobApplicationViewModel createJobApplicationViewModel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String jobApplicationId = facesContext.getExternalContext().getRequestParameterMap().get("id");
        if (jobApplicationId == null) {
            return null;
        }

        return modelMapper.map(jobApplicationService.getById(jobApplicationId), JobApplicationViewModel.class);
    }

    public void deleteJob() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();

        String id = params.get("id");
        jobApplicationService.deleteJob(id);


        FacesContext.getCurrentInstance().getExternalContext().redirect("/");
    }

    public JobApplicationViewModel getJobApplicationViewModel() {
        return jobApplicationViewModel;
    }

    public void setJobApplicationViewModel(JobApplicationViewModel jobApplicationViewModel) {
        this.jobApplicationViewModel = jobApplicationViewModel;
    }

    public JobApplicationService getJobApplicationService() {
        return jobApplicationService;
    }

    public void setJobApplicationService(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
