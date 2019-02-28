package app.beans;

import app.model.binding.ScheduleBindingModel;
import app.model.view.ScheduleViewModel;
import app.service.api.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AllSchedulesBean {

    @Inject
    private DocumentService documentService;

    public List<ScheduleViewModel> allSchedules() {
        return documentService.getAll().stream()
                .map(scheduleServiceModel -> {
                    ScheduleViewModel scheduleViewModel = new ScheduleViewModel();
                    scheduleViewModel.setTitle(scheduleServiceModel.getTitle().length() <= 12 ?
                            scheduleServiceModel.getTitle() : scheduleServiceModel.getTitle().substring(0, 12) + "...");
                    scheduleViewModel.setContent(scheduleServiceModel.getContent());
                    scheduleViewModel.setId(scheduleServiceModel.getId());

                    return scheduleViewModel;
                })
                .collect(Collectors.toList());
    }

    public List<Integer> loop(int number) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(i);
        }

        return list;
    }
}
