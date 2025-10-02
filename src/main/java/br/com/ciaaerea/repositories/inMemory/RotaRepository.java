package br.com.ciaaerea.repositories.inMemory;

import br.com.ciaaerea.domain.model.Rota;
import br.com.ciaaerea.repositories.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RotaRepository implements Repository<Rota> {

    List<Rota> rotas = new ArrayList<>();

    @Override
    public void add(Rota rota) {
        rotas.add(rota);
    }

    @Override
    public List<Rota> findAll() {
        return new ArrayList<>(rotas);
    }

    @Override
    public Rota findByIndex(int index) {
        return rotas.get(index);
    }
}
