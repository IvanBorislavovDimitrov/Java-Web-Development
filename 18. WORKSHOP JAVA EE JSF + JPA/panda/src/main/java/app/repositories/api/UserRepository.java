package app.repositories.api;

import app.entities.User;
import app.repositories.GenericRepository;

import java.util.List;

public interface UserRepository extends GenericRepository<User> {

    long getNumberOfUsers();

    User getByUsername(String username);

    User getByUsernameAndPassword(String username, String password);

    List<User> getAllUsers();
}
