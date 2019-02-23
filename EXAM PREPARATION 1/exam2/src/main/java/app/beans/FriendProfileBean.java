package app.beans;

import app.model.view.UserViewModel;
import app.service.api.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class FriendProfileBean {

    @Inject
    private UserService userService;

    @Inject
    private ModelMapper modelMapper;

    private UserViewModel userViewModel;

    @PostConstruct
    public void init() {
        userViewModel = getProfile();
    }

    private UserViewModel getProfile() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String username = facesContext.getExternalContext().getRequestParameterMap().get("user");

        return modelMapper.map(userService.getByUsername(username), UserViewModel.class);
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
