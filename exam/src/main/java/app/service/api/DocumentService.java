package app.service.api;

import app.model.service.ScheduleServiceModel;

import java.util.List;

public interface DocumentService {

    ScheduleServiceModel save(ScheduleServiceModel scheduleServiceModel);

    ScheduleServiceModel getById(String id);

    List<ScheduleServiceModel> getAll();

    void deleteById(String id);
}
