package br.com.ciaaerea.domain.model;

import br.com.ciaaerea.domain.enums.StatusReserva;

public class Reserva {
    private final Passageiro passageiro;
    private final Voo voo;
    private StatusReserva status = StatusReserva.ABERTA;

    public Reserva(Passageiro passageiro, Voo voo){
        this.passageiro = passageiro;
        this.voo = voo;
    }

    public void confirmar (){}
    public void cancelar(){}
    public void embarcar(){}

    public StatusReserva getStatus(){return status;}
}
