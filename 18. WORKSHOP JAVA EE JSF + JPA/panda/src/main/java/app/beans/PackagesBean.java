package app.beans;

import app.constatns.Constants;
import app.entities.Package;
import app.enums.Status;
import app.model.service.PackageServiceModel;
import app.model.view.PackageCreateViewModel;
import app.model.view.PackageViewModel;
import app.model.view.ReceiptViewModel;
import app.services.api.PackageService;
import app.services.api.ReceiptService;
import app.services.api.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
public class PackagesBean {

    private final PackageService packageService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ReceiptService receiptService;

    private PackageCreateViewModel packageCreateViewModel;

    @Inject
    public PackagesBean(PackageService packageService, ModelMapper modelMapper, UserService userService, ReceiptService receiptService) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.receiptService = receiptService;
    }

    @PostConstruct
    public void init() {
        packageCreateViewModel = new PackageCreateViewModel();
    }

    public List<PackageViewModel> allPendingPackages() {
        return packageService.getAllWithStatusPending().stream()
                .map(p -> modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> allShippedPackages() {
        return packageService.getAllWithStatusShipped().stream()
                .map(p -> modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> allDeliveredPackages() {
        return packageService.getAllWithStatusDelivered().stream()
                .map(p -> modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());
    }

    public void addPackage() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            packageService.save(packageCreateViewModel);
        } catch (IllegalArgumentException e) {
            facesContext.getExternalContext().redirect("/packages/create");
            return;
        }

        facesContext.getExternalContext().redirect("/");
    }

    public PackageViewModel packageDetails() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        Map<String, String> requestParameterMap = facesContext.getExternalContext().getRequestParameterMap();

        PackageViewModel packageViewModel = modelMapper.map(packageService
                .getByUUID(requestParameterMap.get("id")), PackageViewModel.class);
        if (packageViewModel.getStatus().toLowerCase().equals(Status.PENDING.toString().toLowerCase())) {
            packageViewModel.setEstimatedDeliveryDate("N/A");
        } else if (packageViewModel.getStatus().toLowerCase().equals(Status.DELIVERED.toString().toLowerCase()) ||
                packageViewModel.getStatus().toLowerCase().equals(Status.ACQUIRED.toString().toLowerCase())) {
            packageViewModel.setEstimatedDeliveryDate("Delivered");
        }

        return packageViewModel;
    }

    public void shipPackage(String packageUUID) throws IOException {
        packageService.shipPackage(packageUUID);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("/");
    }

    public void deliverPackage(String packageUUID) throws IOException {
        packageService.deliverPackage(packageUUID);
        PackageServiceModel packageServiceModel = packageService.getByUUID(packageUUID);
        PackageViewModel packageViewModel = modelMapper.map(packageServiceModel, PackageViewModel.class);

        ReceiptViewModel receiptViewModel = new ReceiptViewModel();
        receiptViewModel.setFee(String.valueOf(packageServiceModel.getWeight().multiply(BigDecimal.valueOf(2.67))));
        receiptViewModel.setIssuedOn(LocalDate.now().toString());
        receiptViewModel.setPackageViewModel(packageViewModel);

        receiptService.save(receiptViewModel);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("/");
    }


    public List<String> allRegisteredUsers() {
        return userService.getAllUsers();
    }

    public PackageService getPackageService() {
        return packageService;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public PackageCreateViewModel getPackageCreateViewModel() {
        return packageCreateViewModel;
    }

    public void setPackageCreateViewModel(PackageCreateViewModel packageCreateViewModel) {
        this.packageCreateViewModel = packageCreateViewModel;
    }

}
