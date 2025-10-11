package br.com.ciaaerea.infra.repositories;

import java.util.List;

public interface Repository<T> {
    void save(T object);
    List<T> findAll();
    T findByIndex(int index);
}
