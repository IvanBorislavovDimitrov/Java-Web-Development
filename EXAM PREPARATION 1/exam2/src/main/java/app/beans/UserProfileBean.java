package app.beans;

import app.constants.Constants;
import app.model.view.UserViewModel;
import app.service.api.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class UserProfileBean {

    @Inject
    private UserService userService;

    @Inject
    private ModelMapper modelMapper;

    private UserViewModel userViewModel;

    @PostConstruct
    public void init() {
        userViewModel = getLoggedUser();
    }

    private UserViewModel getLoggedUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        String loggedUser = (String) httpSession.getAttribute(Constants.LOGGED_USER);

        return modelMapper.map(userService.getByUsername(loggedUser), UserViewModel.class);
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

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }
}
