package br.com.ciaaerea.domain.model;

import br.com.ciaaerea.domain.exceptions.BusinessViolationException;
import br.com.ciaaerea.infra.cli.util.StringFormatter;

import java.util.Objects;

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
            throw new BusinessViolationException("Documento não pode estar em branco");
        }
    }

    private void validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11){
            throw new BusinessViolationException("CPF deve ter 11 caracteres");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public String getDocumento() {
        return documento;
    }

    @Override
    public String toString() {
//        return String.format("Passageiro [ Nome: %s ] CPF: %S, Documento: %s ]",nome, cpf,documento);
        return
                StringFormatter.formatProp("Nome", 9, nome) +
                        StringFormatter.formatProp("CPF", 9, cpf) +
                        StringFormatter.formatProp("Documento", 9, documento);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Passageiro that = (Passageiro) o;
        return Objects.equals(cpf, that.cpf) && Objects.equals(documento, that.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, documento);
    }
}
