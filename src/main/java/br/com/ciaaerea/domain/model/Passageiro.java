package br.com.ciaaerea.domain.model;

public final class Passageiro extends Pessoa{
    private final String cpf;
    private String documento;

    public Passageiro(String nome, String cpf, String documento){
        super(nome);
        validarCPF(cpf);
        this.cpf = cpf;
        this.documento = documento;
    }

    private void validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11){
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public String getDocumento() {
        return documento;
    }
}
