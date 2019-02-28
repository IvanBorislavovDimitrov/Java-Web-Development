package app.service.impl;

import app.entities.Document;
import app.model.service.ScheduleServiceModel;
import app.repositories.api.DocumentRepository;
import app.service.api.DocumentService;
import app.util.ValidationUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository scheduleRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository scheduleRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ScheduleServiceModel save(ScheduleServiceModel scheduleServiceModel) {
        checkIsValid(scheduleServiceModel);
        return modelMapper.map(scheduleRepository.save(modelMapper.map(scheduleServiceModel, Document.class)), ScheduleServiceModel.class);
    }

    @Override
    public ScheduleServiceModel getById(String id) {
        Document document = scheduleRepository.getById(id);
        if (document == null) {
            return null;
        }
        return modelMapper.map(document, ScheduleServiceModel.class);
    }

    @Override
    public List<ScheduleServiceModel> getAll() {
        return scheduleRepository.getAll().stream()
                .map(document -> modelMapper.map(document, ScheduleServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        scheduleRepository.deleteById(id);
    }

    private void checkIsValid(ScheduleServiceModel scheduleServiceModel) {
        if (!ValidationUtil.isValid(scheduleServiceModel)) {
            throw new IllegalArgumentException("Invalid Document");
        }
        if ("".equals(scheduleServiceModel.getTitle()) || "".equals(scheduleServiceModel.getContent())) {
            throw new IllegalArgumentException("Invalid document");
        }
    }
}
