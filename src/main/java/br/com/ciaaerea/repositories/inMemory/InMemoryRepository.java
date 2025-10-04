package br.com.ciaaerea.repositories.inMemory;

import br.com.ciaaerea.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public abstract class InMemoryRepository <T> implements Repository<T> {
    List<T> list = new ArrayList<>();

    @Override
    public void add(T T) {
        list.add(T);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(list);
    }

    @Override
    public T findByIndex(int index) {
        return list.get(index);
    }
}
