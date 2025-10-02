package br.com.ciaaerea.repositories.inMemory;
import br.com.ciaaerea.domain.model.Voo;
import br.com.ciaaerea.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class VooRepository implements Repository<Voo> {

    List<Voo> voos = new ArrayList<>();

    @Override
    public void add(Voo Voo) {
        voos.add(Voo);
    }

    @Override
    public List<Voo> findAll() {
        return new ArrayList<>(voos);
    }

    @Override
    public Voo findByIndex(int index) {
        return voos.get(index);
    }
}
