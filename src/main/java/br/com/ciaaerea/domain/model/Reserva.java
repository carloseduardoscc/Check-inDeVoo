package br.com.ciaaerea.domain.model;

import br.com.ciaaerea.domain.enums.StatusReserva;

public class Reserva {
    private final Passageiro passageiro;
    private Assento assento;
    private StatusReserva status = StatusReserva.ABERTA;

    public Reserva(Passageiro passageiro, Assento assento){
        this.assento = assento;
        this.passageiro = passageiro;
    }

    public boolean isAtiva (){
         return status != StatusReserva.CANCELADA;
    }

    public void setStatus(StatusReserva novoStatus){
        this.status.validarTransicao(novoStatus);
        this.status = novoStatus;
    }

    public Assento getAssento() {
        return assento;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public StatusReserva getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "passageiro=" + passageiro +
                ", assento=" + assento +
                ", status=" + status +
                '}';
    }
}
