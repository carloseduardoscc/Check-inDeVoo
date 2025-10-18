package br.com.ciaaerea.domain.model;

import br.com.ciaaerea.domain.exceptions.BusinessViolationException;

public abstract class Pessoa {
    protected String nome;

    protected Pessoa(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new BusinessViolationException("Nome do passageiro não pode estar em branco");
        }
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return String.format("[ %s ]",nome);
    }
}
