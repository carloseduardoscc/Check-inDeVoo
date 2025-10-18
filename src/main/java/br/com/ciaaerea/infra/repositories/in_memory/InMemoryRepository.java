package br.com.ciaaerea.infra.repositories.in_memory;

import br.com.ciaaerea.domain.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository <T> implements Repository<T> {
    List<T> list = new ArrayList<>();

    @Override
    public void save(T T) {
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
