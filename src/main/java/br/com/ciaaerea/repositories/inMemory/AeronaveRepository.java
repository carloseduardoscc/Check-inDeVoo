package br.com.ciaaerea.repositories.inMemory;

import br.com.ciaaerea.domain.model.Aeronave;
import br.com.ciaaerea.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class AeronaveRepository implements Repository<Aeronave> {

    List<Aeronave> aeronave = new ArrayList<>();

    @Override
    public void add(Aeronave Aeronave) {
        aeronave.add(Aeronave);
    }

    @Override
    public List<Aeronave> findAll() {
        return new ArrayList<>(aeronave);
    }

    @Override
    public Aeronave findByIndex(int index) {
        return aeronave.get(index);
    }
}
