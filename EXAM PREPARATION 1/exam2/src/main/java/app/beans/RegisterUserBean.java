package app.beans;

import app.model.binding.UserRegisterBindingModel;
import app.model.service.UserServiceModel;
import app.service.api.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class RegisterUserBean {

    private UserRegisterBindingModel userRegisterViewModel;

    @Inject
    private UserService userService;

    @Inject
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        userRegisterViewModel = new UserRegisterBindingModel();
    }

    public void register() throws IOException {
        userService.save(modelMapper.map(userRegisterViewModel, UserServiceModel.class));
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("/");
    }

    public UserRegisterBindingModel getUserRegisterViewModel() {
        return userRegisterViewModel;
    }

    public void setUserRegisterViewModel(UserRegisterBindingModel userRegisterViewModel) {
        this.userRegisterViewModel = userRegisterViewModel;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
