package br.com.ciaaerea.domain.model;

public final class Passageiro extends Pessoa{
    private final String cpf;
    private String documento;

    public Passageiro(String nome, String cpf, String documento){
        super(nome);
        this.cpf = cpf;
        this.documento = documento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDocumento() {
        return documento;
    }
}
