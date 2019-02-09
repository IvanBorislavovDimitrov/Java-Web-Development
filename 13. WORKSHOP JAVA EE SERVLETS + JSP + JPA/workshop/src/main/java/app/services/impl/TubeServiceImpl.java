package app.services.impl;

import app.entities.Tube;
import app.entities.User;
import app.enums.TubeStatus;
import app.mappers.Mapper;
import app.model.TubeBindingEntity;
import app.model.views.TubeViewModel;
import app.repositories.api.TubeRepository;
import app.repositories.api.UserRepository;
import app.services.api.TubeService;
import app.util.ValidationUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final Mapper mapper;
    private final UserRepository userRepository;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, Mapper mapper, UserRepository userRepository) {
        this.tubeRepository = tubeRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<TubeBindingEntity> getAll() {
        return tubeRepository.getAll().stream()
                .map(tube -> mapper.map(tube, TubeBindingEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public TubeBindingEntity getObj(String str) {
        Tube tube = tubeRepository.getObj(str);

        return mapper.map(tube, TubeBindingEntity.class);
    }

    @Override
    public void persist(TubeBindingEntity tubeBindingModel) {
        if (!ValidationUtil.isValid(tubeBindingModel)) {
            throw new IllegalArgumentException("Invalid Tube");
        }
        User user = userRepository.getObj(tubeBindingModel.getUploader());
        Tube tube = mapper.map(tubeBindingModel, Tube.class);
        tube.setTubeStatus(TubeStatus.PENDING);
        tube.setUploader(user);

        tubeRepository.persist(tube);
    }

    @Override
    public List<TubeBindingEntity> getAllByUsername(String username) {
        return tubeRepository.getAllByUsername(username).stream()
                .map(tube -> mapper.map(tube, TubeBindingEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addView(String tubeUuid) {
        tubeRepository.addView(tubeUuid);
    }

    @Override
    public List<TubeViewModel> getAllPendingTubes() {
        return tubeRepository.getAllPendingTubes().stream()
                .map(tube -> mapper.map(tube, TubeViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TubeBindingEntity> getAllApprovedTubes() {
        return tubeRepository.getAllApprovedTubes().stream()
                .map(tube -> mapper.map(tube, TubeBindingEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public void setTubeStatus(String uuid, TubeStatus tubeStatus) {
        tubeRepository.setTubeStatus(uuid, tubeStatus);
    }
}
