package app.service.api;

import app.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel save(UserServiceModel userServiceModel);

    UserServiceModel getById(String id);

    List<UserServiceModel> getAll();

    boolean isUserPresented(String username, String password);
}