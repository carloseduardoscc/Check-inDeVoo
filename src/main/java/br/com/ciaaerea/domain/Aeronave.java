package br.com.ciaaerea.domain;

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

    public Assento findAssentoByCode(String code){
        for(List<Assento> fileira : assentos){
            for(Assento assento : fileira){
                if (assento.getCode().equals(code)){
                    return assento;
                }
            }
        }
        throw new IllegalArgumentException("Assento não disponível nesta aeronave");
    }

    public boolean hasAssento(Assento assento){
        return assentos.stream()
                .flatMap(List::stream)
                .anyMatch(a->a.equals(assento));
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
