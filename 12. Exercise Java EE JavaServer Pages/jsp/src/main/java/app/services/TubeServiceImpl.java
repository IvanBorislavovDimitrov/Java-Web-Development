package app.services;

import app.dtos.TubeServiceDto;
import app.entities.Tube;
import app.repositories.TubeRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {

    @Inject
    private TubeRepository tubeRepository;

    @Override
    public List<TubeServiceDto> getAll() {
        return tubeRepository.getAll()
                .stream()
                .map(t -> new TubeServiceDto(t.getName(), t.getDescription(), t.getYoutubeLink(), t.getUploader()))
                .collect(Collectors.toList());
    }

    @Override
    public TubeServiceDto getObj(String val) {
        Tube t = tubeRepository.getObj(val);

        return new TubeServiceDto(t.getName(), t.getDescription(), t.getYoutubeLink(), t.getUploader());
    }

    @Override
    public void save(TubeServiceDto obj) {
        tubeRepository.save(new Tube(obj.getTitle(), obj.getDescription(), obj.getYoutubeLink(), obj.getUploader()));
    }
}
