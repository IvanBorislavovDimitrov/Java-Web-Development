package app.beans;

import app.constants.Constants;
import app.service.api.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class AddFriendBean {

    @Inject
    private UserService userService;

    public void addFriend(String username) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);

        userService.addFriend((String) httpSession.getAttribute(Constants.LOGGED_USER), username);
    }
}
