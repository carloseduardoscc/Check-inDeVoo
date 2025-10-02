package br.com.ciaaerea.repositories;

import java.util.List;

public interface Repository<T> {
    void add(T object);
    List<T> findAll();
    T findByIndex(int index);
}
