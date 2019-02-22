package app.services.api;

import app.model.service.ReceiptServiceModel;
import app.model.view.ReceiptViewModel;

import java.util.List;

public interface ReceiptService {

    List<ReceiptServiceModel> getAllByRecipient(String recipient);

    ReceiptServiceModel getByUsernameAndUUID(String username, String uuid);

    void save(ReceiptViewModel receiptViewModel);
}
