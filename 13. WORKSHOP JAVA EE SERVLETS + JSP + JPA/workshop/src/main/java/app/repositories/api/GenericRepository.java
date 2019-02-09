package app.repositories.api;

import java.util.List;

public interface GenericRepository<T> {

    List<T> getAll();

    T getObj(String str);

    void persist(T obj);
}
