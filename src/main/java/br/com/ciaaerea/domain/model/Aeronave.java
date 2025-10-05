package br.com.ciaaerea.domain.model;

import java.util.List;

public final class Aeronave {
    private final String modelo;
    private final int capacidade;
    private final List<List<Assento>> assentos;

    public Aeronave(String modelo, int capacidade, int assentosPorFileira) {
        this.modelo = modelo;
        this.capacidade = capacidade;
        assentos = Assento.gerarLayoutDeAssentos(capacidade, assentosPorFileira);
    }

    public List<List<Assento>> getAssentos() {
        return assentos;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
    public String toString() {
        return String.format("modelo - %s | capacidade - %d", getModelo(), getCapacidade());
    }
}
