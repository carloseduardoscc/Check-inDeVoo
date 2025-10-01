package br.com.ciaaerea.domain.model;

import java.util.List;

public class Voo {
    private Aeronave aeronave;
    private List<Reserva> reservas;

    public Voo(Aeronave aeronave) {
        this.aeronave = aeronave;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}
