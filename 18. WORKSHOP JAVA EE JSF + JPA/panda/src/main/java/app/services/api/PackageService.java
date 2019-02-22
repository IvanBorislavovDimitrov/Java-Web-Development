package app.services.api;

import app.model.service.PackageServiceModel;
import app.model.view.PackageCreateViewModel;

import java.util.List;

public interface PackageService {

    List<PackageServiceModel> getAllWithStatusPending();

    List<PackageServiceModel> getAllWithStatusShipped();

    List<PackageServiceModel> getAllWithStatusDelivered();

    PackageServiceModel getByUUID(String uuid);

    void save(PackageCreateViewModel packageCreateViewModel);

    void shipPackage(String uuid);

    void deliverPackage(String uuid);
}
