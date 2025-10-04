package br.com.ciaaerea.domain.enums;

import br.com.ciaaerea.domain.exceptions.TransicaoInvalidaException;

import java.util.Set;

public enum StatusReserva {
    ABERTA("Aberta"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    EMBARCADA("Embarcada");

    private String nome;
    private Set<StatusReserva> possibleTransition;

    static{
        ABERTA.setPossibleTransition(Set.of(CONFIRMADA, CANCELADA));
        CONFIRMADA.setPossibleTransition(Set.of(CANCELADA, EMBARCADA));
        CANCELADA.setPossibleTransition(Set.of());
        EMBARCADA.setPossibleTransition(Set.of());
    }

    StatusReserva(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setPossibleTransition(Set<StatusReserva> possibleTransition) {
        this.possibleTransition = possibleTransition;
    }

    public Set<StatusReserva> getPossibleTransition() {
        return possibleTransition;
    }

    public void validarTransicao(StatusReserva novoStatus) {
        if (!this.getPossibleTransition().contains(novoStatus)){
            throw new TransicaoInvalidaException(String.format("Não é possível que uma reserva %s se torne %s!", this.getNome(), novoStatus.getNome()));
        }
    }
}
