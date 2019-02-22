package app.repositories;

import app.entities.UUIDEntity;

import java.util.List;

public interface GenericRepository<T extends UUIDEntity> {

    List<T> getAll();

    T getByUUID(String uuid);

    void save(T entity);

    void delete(T entity);

    void delete(String uuid);
}
