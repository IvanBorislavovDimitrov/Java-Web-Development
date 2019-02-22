package app.services.impl;

import app.entities.Package;
import app.entities.Receipt;
import app.entities.User;
import app.enums.Role;
import app.model.service.UserRegisterServiceModel;
import app.model.service.UserServiceModel;
import app.repositories.api.UserRepository;
import app.services.api.UserService;
import app.util.PasswordEncoder;
import app.util.ValidationUtil;
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
    public UserServiceModel getUserByUsernameAndPassword(String username, String password) {
        String encodedPassword = PasswordEncoder.encode(password);
        User user = userRepository.getByUsernameAndPassword(username, encodedPassword);

        if (user == null) {
            return null;
        }

        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername(user.getUsername());
        userServiceModel.setEmail(user.getEmail());
        userServiceModel.setRole(user.getRole().name());
        userServiceModel.setPackages(user.getPackages().stream().map(Package::getShippingAddress).collect(Collectors.toList()));
        userServiceModel.setReceipts(user.getReceipts().stream().map(r -> r.getFee().toString()).collect(Collectors.toList()));

        return userServiceModel;
    }

    @Override
    public void save(UserRegisterServiceModel entity) {
        User user = modelMapper.map(entity, User.class);
        user.setRole(userRepository.getNumberOfUsers() == 0 ? Role.ADMIN : Role.USER);
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        if (!ValidationUtil.isValid(user)) {
            throw new IllegalArgumentException("Invalid User!");
        }

        userRepository.save(user);
    }

    @Override
    public void delete(String uuid) {
        userRepository.delete(uuid);
    }

    @Override
    public List<String> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
}
