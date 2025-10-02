package br.com.ciaaerea.repositories;

import java.util.List;

public interface Repository<T> {
    void add(T rota);
    List<T> findAll();
}
