package app.services;

import app.model.CatServiceModel;

import java.util.List;

public interface CatService {

    List<CatServiceModel> getAll();

    CatServiceModel getObj(String str);

    void add(CatServiceModel obj);
}
