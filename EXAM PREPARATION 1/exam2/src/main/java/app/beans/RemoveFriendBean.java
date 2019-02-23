package app.beans;

import app.constants.Constants;
import app.repositories.api.UserRepository;
import app.service.api.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class RemoveFriendBean {

    @Inject
    private UserService userService;

    public void removeFriend(String username) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        String loggedUser = (String) httpSession.getAttribute(Constants.LOGGED_USER);

        userService.removeFriend(loggedUser, username);

        facesContext.getExternalContext().redirect("/friends");
    }
}
