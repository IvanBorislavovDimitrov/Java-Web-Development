package app.services.api;

import app.model.service.UserRegisterServiceModel;
import app.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel getUserByUsernameAndPassword(String username, String password);

    void save(UserRegisterServiceModel entity);

    void delete(String uuid);

    List<String> getAllUsers();
}
