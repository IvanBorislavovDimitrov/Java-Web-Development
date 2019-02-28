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
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            userService.save(modelMapper.map(userRegisterViewModel, UserServiceModel.class));
        } catch (IllegalArgumentException e) {
            facesContext.getExternalContext().redirect("/register");
            return;
        }

        facesContext.getExternalContext().redirect("/login");
    }

    public UserRegisterBindingModel getUserRegisterViewModel() {
        return userRegisterViewModel;
    }

    public void setUserRegisterViewModel(UserRegisterBindingModel userRegisterViewModel) {
        this.userRegisterViewModel = userRegisterViewModel;
    }
}
