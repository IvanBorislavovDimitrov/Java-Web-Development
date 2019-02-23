package app.repositories.api;

import app.entities.User;
import app.repositories.GenericRepository;

public interface UserRepository extends GenericRepository<User, String> {

    boolean isUserPresented(String username, String password);
}
