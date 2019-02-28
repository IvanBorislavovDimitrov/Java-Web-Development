package app.beans;

import app.constants.Constants;
import app.model.binding.UserLoginBindingModel;
import app.service.api.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {

    private UserLoginBindingModel userLoginBindingModel;

    @Inject
    private UserService userService;

    @PostConstruct
    public void init() {
        userLoginBindingModel = new UserLoginBindingModel();
    }

    public void login() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);

        boolean isUserPresented =
                userService.isUserPresented(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());
        if (!isUserPresented) {
            facesContext.getExternalContext().redirect("/login");
            return;
        }

        httpSession.setAttribute(Constants.LOGGED_USER, userLoginBindingModel.getUsername());
        facesContext.getExternalContext().redirect("/home");
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }
}
