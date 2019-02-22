package app.beans;

import app.model.service.UserRegisterServiceModel;
import app.model.view.UserRegisterViewModel;
import app.services.api.UserService;
import org.modelmapper.ModelMapper;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
public class UserRegisterBean {

    private UserRegisterViewModel userRegisterViewModel;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        userRegisterViewModel = new UserRegisterViewModel();
    }

    public void registerUser() throws IOException {
        UserRegisterServiceModel userRegisterServiceModel = modelMapper.map(userRegisterViewModel, UserRegisterServiceModel.class);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            userService.save(userRegisterServiceModel);
        } catch (IllegalArgumentException e) {
            facesContext.getExternalContext().redirect("/");
            return;
        }

        facesContext.getExternalContext().redirect("/");
    }

    public UserRegisterViewModel getUserRegisterViewModel() {
        return userRegisterViewModel;
    }

    public void setUserRegisterViewModel(UserRegisterViewModel userRegisterViewModel) {
        this.userRegisterViewModel = userRegisterViewModel;
    }
}
