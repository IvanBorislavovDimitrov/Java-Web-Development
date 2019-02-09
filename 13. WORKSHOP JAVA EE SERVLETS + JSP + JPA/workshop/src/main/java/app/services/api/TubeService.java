package app.services.api;

import app.enums.TubeStatus;
import app.model.TubeBindingEntity;
import app.model.views.TubeViewModel;

import java.util.Arrays;
import java.util.List;

public interface TubeService {

    List<TubeBindingEntity> getAll();

    TubeBindingEntity getObj(String str);

    void persist(TubeBindingEntity obj);

    List<TubeBindingEntity> getAllByUsername(String username);

    void addView(String tubeUuid);

    List<TubeViewModel> getAllPendingTubes();

    List<TubeBindingEntity> getAllApprovedTubes();

    void setTubeStatus(String uuid, TubeStatus tubeStatus);
}
