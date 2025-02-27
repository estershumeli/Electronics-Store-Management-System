package repository;

import java.util.Collection;

public interface CRUDRepository<T> {
    T create(T t);

    T findById(String id);

    Collection<T> findAll();

    T update(T t);

    T delete(T t);
}
