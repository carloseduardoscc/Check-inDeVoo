package br.com.ciaaerea.domain.model;

import br.com.ciaaerea.domain.exceptions.AssentoIndisponivelException;

import java.util.List;
import java.util.Optional;

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
        throw new AssentoIndisponivelException("Assento não disponível nesta aeronave");
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
