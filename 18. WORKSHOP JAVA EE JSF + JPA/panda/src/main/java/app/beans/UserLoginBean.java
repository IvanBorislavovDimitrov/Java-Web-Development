package app.beans;

import app.constatns.Constants;
import app.entities.User;
import app.model.service.UserServiceModel;
import app.model.view.UserLoginViewModel;
import app.services.api.UserService;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
public class UserLoginBean {

    private UserLoginViewModel userLoginViewModel;
    private final UserService userService;

    @Inject
    public UserLoginBean(UserService userService) {
        this.userService = userService;
        userLoginViewModel = new UserLoginViewModel();
    }

    public void login() throws IOException {
        UserServiceModel userServiceModel = userService.getUserByUsernameAndPassword(userLoginViewModel.getUsername(),
                userLoginViewModel.getPassword());
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (userServiceModel == null) {
            facesContext.getExternalContext().redirect("/jsf/login.xhtml");
            return;
        }
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);

        httpSession.setAttribute(Constants.LOGGED_USER, userServiceModel.getUsername());
        httpSession.setAttribute(Constants.USER_ROLE, userServiceModel.getRole());

        facesContext.getExternalContext().redirect("/");
    }

    public void logout() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        httpSession.invalidate();

        facesContext.getExternalContext().redirect("/");
    }

    public String getLoggedUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);

        return (String) httpSession.getAttribute(Constants.LOGGED_USER);
    }

    public UserLoginViewModel getUserLoginViewModel() {
        return userLoginViewModel;
    }

    public void setUserLoginViewModel(UserLoginViewModel userLoginViewModel) {
        this.userLoginViewModel = userLoginViewModel;
    }
}
