package app.services;

import app.entities.Cat;
import app.model.CatServiceModel;
import app.repostitories.CatRepository;
import app.util.ValidationUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CatServiceModel> getAll() {
        return catRepository.getAll().stream()
                .map(cat -> modelMapper.map(cat, CatServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CatServiceModel getObj(String str) {
        return modelMapper.map(catRepository.getObj(str), CatServiceModel.class);
    }

    @Override
    public void add(CatServiceModel obj) {
        if (!ValidationUtil.isValid(obj)) {
            throw new IllegalArgumentException("Invalid entity!");
        }
        catRepository.add(modelMapper.map(obj, Cat.class));
    }
}
