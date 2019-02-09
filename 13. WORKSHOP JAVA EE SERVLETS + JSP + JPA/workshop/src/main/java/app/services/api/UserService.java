package app.services.api;

import app.entities.User;
import app.enums.UserRole;
import app.model.UserRegisterBindingEntity;

import java.util.List;

public interface UserService {
    List<UserRegisterBindingEntity> getAll();

    UserRegisterBindingEntity getObj(String username);

    void persist(UserRegisterBindingEntity user);

    boolean doesUserExist(String username, String password);

    UserRole getUserRole(String username);

}
