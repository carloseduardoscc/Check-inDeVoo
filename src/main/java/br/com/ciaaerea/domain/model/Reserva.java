package br.com.ciaaerea.domain.model;

import br.com.ciaaerea.domain.enums.StatusReserva;

public class Reserva {
    private final Passageiro passageiro;
    private final Voo voo;
    private StatusReserva status = StatusReserva.ABERTA;

    public Reserva(Passageiro passageiro, Voo voo){
        voo.getReservas().add(this);
        this.passageiro = passageiro;
        this.voo = voo;
    }

    public void setStatus(StatusReserva novoStatus){
        this.status.validarTransicao(novoStatus);
        this.status = novoStatus;
    }



    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public StatusReserva getStatus() {
        return status;
    }
}
