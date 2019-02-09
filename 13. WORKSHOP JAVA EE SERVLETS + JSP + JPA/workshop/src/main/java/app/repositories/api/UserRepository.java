package app.repositories.api;

import app.entities.User;
import app.enums.UserRole;

public interface UserRepository extends GenericRepository<User> {

    boolean doesUserExist(String username, String password);

    boolean doesEmailExist(String email);

    boolean doesUsernameExist(String username);

    long usersCount();

    UserRole getUserRole(String username);
}
