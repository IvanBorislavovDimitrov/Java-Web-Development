package app.beans;

import app.model.service.ScheduleServiceModel;
import app.model.view.ScheduleViewModel;
import app.service.api.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class DetailsScheduleBean {

    @Inject
    private DocumentService documentService;

    @Inject
    private ModelMapper modelMapper;

    private ScheduleViewModel scheduleViewModel;

    @PostConstruct
    public void init() throws IOException {
        scheduleViewModel = getFromDatabaseScheduleViewModel();
    }

    private ScheduleViewModel getFromDatabaseScheduleViewModel() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String scheduleId = facesContext.getExternalContext().getRequestParameterMap().get("id");
        if (scheduleId == null) {
            facesContext.getExternalContext().redirect("/");
            return null;
        }
        ScheduleServiceModel scheduleServiceModel = documentService.getById(scheduleId);
        if (scheduleServiceModel == null) {
            facesContext.getExternalContext().redirect("/");
            return null;
        }
        return modelMapper.map(scheduleServiceModel, ScheduleViewModel.class);
    }

    public ScheduleViewModel getScheduleViewModel() {
        return scheduleViewModel;
    }

    public void setScheduleViewModel(ScheduleViewModel scheduleViewModel) {
        this.scheduleViewModel = scheduleViewModel;
    }
}
