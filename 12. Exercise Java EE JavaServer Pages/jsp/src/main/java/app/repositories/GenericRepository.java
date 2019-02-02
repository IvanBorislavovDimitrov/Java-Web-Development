package app.repositories;

import java.util.List;

public interface GenericRepository<T> {

    List<T> getAll();

    T getObj(String val);

    void save(T obj);
}
