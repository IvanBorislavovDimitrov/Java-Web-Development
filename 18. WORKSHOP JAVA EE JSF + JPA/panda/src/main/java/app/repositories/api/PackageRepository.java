package app.repositories.api;

import app.entities.Package;
import app.model.service.PackageServiceModel;
import app.repositories.GenericRepository;

import java.util.List;

public interface PackageRepository extends GenericRepository<Package> {

    List<Package> getAllWithStatusPending();

    List<Package> getAllWithStatusShipped();

    List<Package> getAllWithStatusDelivered();

    void shipPackage(String uuid);

    void deliverPackage(String uuid);

    void update(Package aPackage);
}
