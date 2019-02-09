package app.repositories.api;

import app.entities.Tube;
import app.enums.TubeStatus;

import java.util.List;

public interface TubeRepository extends GenericRepository<Tube> {

    List<Tube> getAllByUsername(String username);

    void addView(String tubeUuid);

    List<Tube> getAllPendingTubes();

    List<Tube> getAllApprovedTubes();

    void setTubeStatus(String uuid, TubeStatus tubeStatus);
}
