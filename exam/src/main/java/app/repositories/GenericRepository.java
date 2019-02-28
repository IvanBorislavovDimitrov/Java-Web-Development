package app.repositories;

import app.entities.BaseEntity;

import java.util.List;

public interface GenericRepository<T extends BaseEntity, Id> {

    T save(T obj);

    T getById(Id id);

    List<T> getAll();
}
