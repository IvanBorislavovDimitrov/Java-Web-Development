package app.services.impl;

import app.entities.Package;
import app.enums.Status;
import app.model.service.PackageServiceModel;
import app.model.view.PackageCreateViewModel;
import app.repositories.api.PackageRepository;
import app.repositories.api.UserRepository;
import app.services.api.PackageService;
import app.util.ValidationUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Inject
    public PackageServiceImpl(PackageRepository packageRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.packageRepository = packageRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<PackageServiceModel> getAllWithStatusPending() {
        return packageRepository.getAllWithStatusPending().stream()
                .map(p -> modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PackageServiceModel> getAllWithStatusShipped() {
        return packageRepository.getAllWithStatusShipped().stream()
                .map(p -> modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PackageServiceModel> getAllWithStatusDelivered() {
        return packageRepository.getAllWithStatusDelivered().stream()
                .map(p -> modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PackageServiceModel getByUUID(String uuid) {
        return modelMapper.map(packageRepository.getByUUID(uuid), PackageServiceModel.class);
    }

    @Override
    public void save(PackageCreateViewModel packageCreateViewModel) {
        Package aPackage = modelMapper.map(packageCreateViewModel, Package.class);
        aPackage.setRecipient(userRepository.getByUsername(packageCreateViewModel.getRecipientName()));
        aPackage.setStatus(Status.PENDING);
        if (!ValidationUtil.isValid(aPackage)) {
            throw new IllegalArgumentException("Invalid package");
        }
        packageRepository.save(aPackage);
    }

    @Override
    public void shipPackage(String uuid) {
        packageRepository.shipPackage(uuid);
        Package aPackage = packageRepository.getByUUID(uuid);
        Random r = new Random();
        aPackage.setEstimatedDeliveryDate(LocalDate.now().plusDays(r.nextInt(20) + 20));
        packageRepository.update(aPackage);
    }

    @Override
    public void deliverPackage(String uuid) {
        packageRepository.deliverPackage(uuid);
    }

}
