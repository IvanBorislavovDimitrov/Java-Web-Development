package app.services;

import app.dtos.TubeServiceDto;
import app.entities.Tube;

import java.util.List;

public interface TubeService {
    List<TubeServiceDto> getAll();

    TubeServiceDto getObj(String val);

    void save(TubeServiceDto obj);
}
