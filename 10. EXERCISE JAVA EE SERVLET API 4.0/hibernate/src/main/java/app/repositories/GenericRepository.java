package app.repositories;

import java.util.List;

public interface GenericRepository<T> {

    T getObj(String name);

    List<T> getAll();

    void save(T obj);
}
