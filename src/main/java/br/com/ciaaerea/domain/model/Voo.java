package br.com.ciaaerea.domain.model;

import br.com.ciaaerea.UI.StringFormatter;
import br.com.ciaaerea.domain.enums.StatusReserva;
import br.com.ciaaerea.domain.exceptions.AssentoIndisponivelException;
import br.com.ciaaerea.domain.exceptions.PassageiroIsAlreadyInVooException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Voo {
    private final Aeronave aeronave;
    private final List<Reserva> reservas = new ArrayList<>();
    private final Rota rota;

    public Voo(Aeronave aeronave, Rota rota) {
        this.aeronave = aeronave;
        this.rota = rota;
    }

    public void addReserva(Reserva reserva) {
        validateNovaReserva(reserva);
        reservas.add(reserva);
    }

    private void validateNovaReserva(Reserva reserva) {
        validateIfAssentoOfReservaIsLivre(reserva);
        validateUniqueReservaForPassageiro(reserva.getPassageiro());
    }

    private void validateUniqueReservaForPassageiro(Passageiro passageiro) {
        boolean jaTemAtiva = reservas.stream().anyMatch(r -> r.getPassageiro().equals(passageiro)
                && r.getStatus() != StatusReserva.CANCELADA);

        if (jaTemAtiva) {
            throw new PassageiroIsAlreadyInVooException("Um mesmo passageiro não pode ter mais de uma reserva em um mesmo voo");
        }
    }

    private void validateIfAssentoOfReservaIsLivre(Reserva reserva) {
        boolean ocupado = reservas.stream()
                .anyMatch(r -> r.getAssento().equals(reserva.getAssento())
                && r.getStatus() != StatusReserva.CANCELADA);

        if (ocupado) {
            throw new AssentoIndisponivelException("Assento " + reserva.getAssento() + " já está ocupado");
        }
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
