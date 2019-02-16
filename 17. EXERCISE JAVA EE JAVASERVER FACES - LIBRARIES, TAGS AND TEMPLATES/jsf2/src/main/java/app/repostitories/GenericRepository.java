package app.repostitories;

import java.util.List;

public interface GenericRepository<T> {

    List<T> getAll();

    T getObj(String str);

    void add(T obj);
}
