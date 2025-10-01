package br.com.ciaaerea.domain.model;

public final class Aeronave {
    private final String modelo;
    private final int capacidade;

    public Aeronave(String modelo, int capacidade) {
        this.modelo = modelo;
        this.capacidade = capacidade;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCapacidade() {
        return capacidade;
    }
}
