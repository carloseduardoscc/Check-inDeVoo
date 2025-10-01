package br.com.ciaaerea.domain.model;

public abstract class Pessoa {
    protected String nome;
    protected Pessoa(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
}
