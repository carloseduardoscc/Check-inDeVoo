package br.com.ciaaerea.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Voo {
    private final Aeronave aeronave;
    private final List<Reserva> reservas;
    private final Rota rota;

    {
        reservas = new ArrayList<>();
    }

    public Voo(Aeronave aeronave, Rota rota) {
        this.aeronave = aeronave;
        this.rota = rota;
    }

    public int getReservasLivres(){
        return aeronave.getCapacidade() - reservas.size();
    }

    public Rota getRota() {
        return rota;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "Voo" +
                "\n" + rota +
                "\n" + aeronave +
                "\nReservas Livres - " + getReservasLivres();
    }
}
