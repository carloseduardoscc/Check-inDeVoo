package br.com.ciaaerea.domain.model;

import br.com.ciaaerea.domain.exceptions.BussinesViolationException;

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
            throw new BussinesViolationException("CPF deve ter 11 caracteres");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public String getDocumento() {
        return documento;
    }
}
