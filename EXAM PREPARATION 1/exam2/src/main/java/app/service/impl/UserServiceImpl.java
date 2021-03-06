package app.service.impl;

import app.entities.User;
import app.model.service.UserServiceModel;
import app.repositories.api.UserRepository;
import app.service.api.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel save(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userRepository.save(user);

        return modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel getById(String id) {
        return modelMapper.map(userRepository.getById(id), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getAll() {
        return userRepository.getAll().stream()
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isUserPresented(String username, String password) {
        return userRepository.isUserPresented(username, DigestUtils.sha256Hex(password));
    }

    @Override
    public List<UserServiceModel> getAvailableForUserFriends(String currentUser) {
        User user = userRepository.getByUsername(currentUser);
        userRepository.refresh(user);
        List<User> allUsers = userRepository.getAll();

        return allUsers.stream()
                .filter(u -> !user.getUsers()
                        .stream()
                        .map(User::getUsername)
                        .collect(Collectors.toList())
                        .contains(u.getUsername()) && !u.getUsername().equals(user.getUsername()))
                .map(u -> modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addFriend(String loggedUser, String username) {
        userRepository.addFriend(loggedUser, username);
    }

    @Override
    public List<UserServiceModel> getFriends(String loggedUser) {
        User user = userRepository.getByUsername(loggedUser);
        userRepository.refresh(user);

        return user.getUsers().stream()
                .map(u -> modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeFriend(String loggedUser, String username) {
        userRepository.removeFriend(loggedUser, username);
    }

    @Override
    public UserServiceModel getByUsername(String loggedUser) {
        return modelMapper.map(userRepository.getByUsername(loggedUser), UserServiceModel.class);
    }
}
