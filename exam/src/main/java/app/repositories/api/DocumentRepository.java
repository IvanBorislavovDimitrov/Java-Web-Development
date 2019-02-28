package app.repositories.api;

import app.entities.Document;
import app.repositories.GenericRepository;

public interface DocumentRepository extends GenericRepository<Document, String> {

    void deleteById(String id);
}
