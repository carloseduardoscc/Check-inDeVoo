package br.com.ciaaerea.domain.model;

import br.com.ciaaerea.domain.exceptions.BussinesViolationException;

public final class Passageiro extends Pessoa{
    private final String cpf;
    private String documento;

    public Passageiro(String nome, String cpf, String documento){
        super(nome);
        validarCPF(cpf);
        validarDocumento(documento);
        this.cpf = cpf;
        this.documento = documento;
    }

    private void validarDocumento(String documento) {
        if (documento == null || documento.isBlank()){
            throw new BussinesViolationException("Documento não pode estar em branco");
        }
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
