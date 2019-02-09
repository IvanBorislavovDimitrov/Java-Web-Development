package app.services.impl;

import app.entities.User;
import app.enums.UserRole;
import app.mappers.Mapper;
import app.model.UserRegisterBindingEntity;
import app.repositories.api.UserRepository;
import app.services.api.UserService;
import app.util.PasswordEncoder;
import app.util.ValidationUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Mapper mapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserRegisterBindingEntity> getAll() {
        return userRepository.getAll().stream()
                .map(user -> mapper.map(user, UserRegisterBindingEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserRegisterBindingEntity getObj(String username) {
        return mapper.map(userRepository.getObj(username), UserRegisterBindingEntity.class);
    }

    @Override
    public void persist(UserRegisterBindingEntity userRegisterBindingEntity) {
        if (!ValidationUtil.isValid(userRegisterBindingEntity)) {
            throw new IllegalArgumentException("Invalid User");
        }
        if (userRepository.doesEmailExist(userRegisterBindingEntity.getEmail()) ||
                userRepository.doesUsernameExist(userRegisterBindingEntity.getUsername())) {
            throw new IllegalArgumentException("Username or email is taken!");
        }
        String encodedPassword = PasswordEncoder.encode(userRegisterBindingEntity.getPassword());
        User user = mapper.map(userRegisterBindingEntity, User.class);
        setUserRole(user);
        user.setPassword(encodedPassword);

        userRepository.persist(user);
    }

    @Override
    public boolean doesUserExist(String username, String password) {
        return userRepository.doesUserExist(username, password);
    }

    @Override
    public UserRole getUserRole(String username) {
        return userRepository.getUserRole(username);
    }

    private void setUserRole(User user) {
        if (userRepository.usersCount() == 0) {
            user.setUserRole(UserRole.ADMIN);
        } else {
            user.setUserRole(UserRole.USER);
        }
    }
}
