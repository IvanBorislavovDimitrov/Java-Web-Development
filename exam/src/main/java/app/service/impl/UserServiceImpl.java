package app.service.impl;

import app.entities.User;
import app.model.service.UserServiceModel;
import app.repositories.api.UserRepository;
import app.service.api.UserService;
import app.util.ValidationUtil;
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
        checkIsModelValid(userServiceModel);
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
    public UserServiceModel getByUsername(String loggedUser) {
        return modelMapper.map(userRepository.getByUsername(loggedUser), UserServiceModel.class);
    }

    private void checkIsModelValid(UserServiceModel userServiceModel) {
        if (!ValidationUtil.isValid(userServiceModel)) {
            throw new IllegalArgumentException("Invalid User");
        }
        if (exists(userServiceModel.getUsername())) {
            throw new IllegalArgumentException("Username is taken");
        }
        if ("".equals(userServiceModel.getPassword()) || "".equals(userServiceModel.getConfirmPassword()) ||
                        !userServiceModel.getPassword().equals(userServiceModel.getConfirmPassword())) {
            throw new IllegalArgumentException("Invalid Password");
        }
    }

    private boolean exists(String username) {
        return userRepository.getByUsername(username) != null;
    }
}
