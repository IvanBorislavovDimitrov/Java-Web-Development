package app.repositories.api;

import app.entities.User;
import app.repositories.GenericRepository;

import java.util.List;

public interface UserRepository extends GenericRepository<User, String> {

    boolean isUserPresented(String username, String password);

    User getByUsername(String username);

    void addFriend(String loggedUser, String username);

    void refresh(User user);

    void removeFriend(String loggedUser, String username);
}
