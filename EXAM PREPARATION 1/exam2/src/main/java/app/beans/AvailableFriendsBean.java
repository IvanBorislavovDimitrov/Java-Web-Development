package app.beans;

import app.constants.Constants;
import app.model.service.UserServiceModel;
import app.model.view.UserViewModel;
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
public class AvailableFriendsBean {

    @Inject
    private UserService userService;

    @Inject
    private ModelMapper modelMapper;

    public List<UserViewModel> availableUsers() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);

        String currentUser = (String) httpSession.getAttribute(Constants.LOGGED_USER);

        return userService.getAvailableForUserFriends(currentUser).stream()
                .map(userServiceModel -> modelMapper.map(userServiceModel, UserViewModel.class))
                .collect(Collectors.toList());

    }
}

