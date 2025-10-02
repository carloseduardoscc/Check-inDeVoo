package br.com.ciaaerea.repositories.inMemory;

import br.com.ciaaerea.domain.model.Aeronave;
import br.com.ciaaerea.domain.model.Rota;
import br.com.ciaaerea.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class AeronaveRepository implements Repository<Aeronave> {

    List<Aeronave> Aeronaves = new ArrayList<>();

    @Override
    public void add(Aeronave Aeronave) {
        Aeronaves.add(Aeronave);
    }

    @Override
    public List<Aeronave> findAll() {
        return new ArrayList<>(Aeronaves);
    }
}
