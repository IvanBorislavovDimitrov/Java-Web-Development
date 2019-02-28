package app.beans;

import app.model.binding.ScheduleBindingModel;
import app.model.service.ScheduleServiceModel;
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
public class ScheduleRegisterBean {

    @Inject
    private DocumentService documentService;

    @Inject
    private ModelMapper modelMapper;

    private ScheduleBindingModel scheduleBindingModel;

    @PostConstruct
    public void init() {
        scheduleBindingModel = new ScheduleBindingModel();
    }

    public void save() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ScheduleServiceModel scheduleServiceModel;
        try {
            scheduleServiceModel = documentService.save(modelMapper.map(scheduleBindingModel, ScheduleServiceModel.class));
        } catch (IllegalArgumentException e) {
            facesContext.getExternalContext().redirect("/schedules/create");
            return;
        }

        facesContext.getExternalContext().redirect("/schedules/details?id=" + scheduleServiceModel.getId());
    }

    public ScheduleBindingModel getScheduleBindingModel() {
        return scheduleBindingModel;
    }

    public void setScheduleBindingModel(ScheduleBindingModel scheduleBindingModel) {
        this.scheduleBindingModel = scheduleBindingModel;
    }
}


