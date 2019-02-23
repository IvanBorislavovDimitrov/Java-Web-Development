package app.beans;

import app.model.service.UserServiceModel;
import app.model.view.UserRegisterViewModel;
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

    private UserRegisterViewModel userRegisterViewModel;

    @Inject
    private UserService userService;

    @Inject
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        userRegisterViewModel = new UserRegisterViewModel();
    }

    public void register() throws IOException {
        userService.save(modelMapper.map(userRegisterViewModel, UserServiceModel.class));
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("/");
    }

    public UserRegisterViewModel getUserRegisterViewModel() {
        return userRegisterViewModel;
    }

    public void setUserRegisterViewModel(UserRegisterViewModel userRegisterViewModel) {
        this.userRegisterViewModel = userRegisterViewModel;
    }
}
