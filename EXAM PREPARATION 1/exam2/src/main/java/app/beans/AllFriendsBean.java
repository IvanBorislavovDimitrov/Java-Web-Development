package app.beans;

import app.constants.Constants;
import app.model.service.UserServiceModel;
import app.service.api.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AllFriendsBean {

    @Inject
    private UserService userService;

    public List<String> friends() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        String loggedUser = (String) httpSession.getAttribute(Constants.LOGGED_USER);

        return userService.getFriends(loggedUser).stream()
                .map(UserServiceModel::getUsername)
                .collect(Collectors.toList());
    }
}
