package app.beans;

import app.model.CatInputViewModel;
import app.model.CatServiceModel;
import app.services.CatService;
import org.modelmapper.ModelMapper;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CatBean {

    private CatInputViewModel catInputViewModel;

    private final CatService catService;
    private final ModelMapper modelMapper;

    @Inject
    public CatBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.catInputViewModel = new CatInputViewModel();
    }

    public void registerCat() throws IOException {
        CatServiceModel catServiceModel = modelMapper.map(catInputViewModel, CatServiceModel.class);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            catService.add(catServiceModel);
        } catch (IllegalArgumentException e) {
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/jsf/index.xhtml");
            return;
        }

        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/jsf/index.xhtml");
    }

    public List<CatInputViewModel> allCats() {
        return catService.getAll().stream()
                .map(catServiceModel -> modelMapper.map(catServiceModel, CatInputViewModel.class))
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public CatInputViewModel getCatInputViewModel() {
        return catInputViewModel;
    }

    public void setCatInputViewModel(CatInputViewModel catInputViewModel) {
        this.catInputViewModel = catInputViewModel;
    }
}
