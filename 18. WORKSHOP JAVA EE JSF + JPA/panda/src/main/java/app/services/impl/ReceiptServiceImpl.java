package app.services.impl;

import app.entities.Receipt;
import app.model.service.ReceiptServiceModel;
import app.model.view.ReceiptViewModel;
import app.repositories.api.PackageRepository;
import app.repositories.api.ReceiptRepository;
import app.repositories.api.UserRepository;
import app.services.api.ReceiptService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ModelMapper modelMapper;
    private final PackageRepository packageRepository;
    private final UserRepository userRepository;

    @Inject
    public ReceiptServiceImpl(ReceiptRepository receiptRepository, ModelMapper modelMapper, PackageRepository packageRepository, UserRepository userRepository) {
        this.receiptRepository = receiptRepository;
        this.modelMapper = modelMapper;
        this.packageRepository = packageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ReceiptServiceModel> getAllByRecipient(String recipient) {
        return receiptRepository.getAllByRecipient(recipient).stream()
                .map(receipt -> modelMapper.map(receipt, ReceiptServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptServiceModel getByUsernameAndUUID(String username, String uuid) {
        return modelMapper.map(receiptRepository.getByUsernameAndUUID(username, uuid), ReceiptServiceModel.class);
    }

    @Override
    public void save(ReceiptViewModel receiptViewModel) {
        Receipt receipt = modelMapper.map(receiptViewModel, Receipt.class);
        receipt.setPackageName(packageRepository.getByUUID(receiptViewModel.getPackageViewModel().getUuid()));
        receipt.setRecipient(userRepository.getByUsername(receiptViewModel.getRecipient()));
        receipt.setIssuedOn(LocalDate.now());

        receiptRepository.save(receipt);
    }
}
