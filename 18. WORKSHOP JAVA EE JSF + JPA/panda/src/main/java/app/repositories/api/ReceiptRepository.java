package app.repositories.api;

import app.entities.Receipt;
import app.repositories.GenericRepository;

import java.util.List;

public interface ReceiptRepository extends GenericRepository<Receipt> {

    List<Receipt> getAllByRecipient(String recipient);

    Receipt getByUsernameAndUUID(String username, String uuid);
}
