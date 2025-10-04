package br.com.ciaaerea.domain.model;

public abstract class Pessoa {
    protected String nome;

    protected Pessoa(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do passageiro não pode estar em branco");
        }
    }

    public String getNome() {
        return this.nome;
    }
}
