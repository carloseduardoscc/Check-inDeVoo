package br.com.ciaaerea.domain.model;

import br.com.ciaaerea.UI.StringFormatter;

import java.util.ArrayList;
import java.util.List;

public class Voo {
    private final Aeronave aeronave;
    private final List<Reserva> reservas = new ArrayList<>();
    private final Rota rota;

    public Voo(Aeronave aeronave, Rota rota) {
        this.aeronave = aeronave;
        this.rota = rota;
    }

    public int getReservasLivres() {
        return aeronave.getCapacidade() - reservas.size();
    }

    public int getReservasOcupadas() {
        return reservas.size();
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
        return
                StringFormatter.formatProp("Rota", 17, rota.toString()) +
                StringFormatter.formatProp("Aeronave", 17, aeronave.toString()) +
                StringFormatter.formatProp("Reservas Livres", 17, Integer.toString(getReservasLivres())) +
                StringFormatter.formatProp("Reservas Ocupadas", 17, Integer.toString(getReservasOcupadas()));
    }
}
