package main.java.myclass.api;

import java.util.List;

public interface Repository<T> {

    T findOne(long id);

    List<T> findAll();

    boolean saveOne(T item);

    boolean saveAll(List<T> items);

    boolean editOne(T item);

    boolean deleteOne(long id);

    boolean deleteAll();
}