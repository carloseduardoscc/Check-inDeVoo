package br.com.ciaaerea.domain.exceptions;

public class TransicaoInvalidaException extends BusinessViolationException {
    public TransicaoInvalidaException(String message){
        super(message);
    }
}
