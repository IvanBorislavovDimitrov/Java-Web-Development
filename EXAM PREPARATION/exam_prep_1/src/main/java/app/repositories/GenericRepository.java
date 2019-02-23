package app.repositories;

import app.entities.BaseEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;

public interface GenericRepository<T extends BaseEntity, Id> {

    T save(T obj);

    T getById(Id id);

    List<T> getAll();
}
