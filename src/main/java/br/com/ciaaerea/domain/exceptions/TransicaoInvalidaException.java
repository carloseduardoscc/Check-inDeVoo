package br.com.ciaaerea.domain.exceptions;

public class TransicaoInvalidaException extends BussinesViolationException {
    public TransicaoInvalidaException(String message){
        super(message);
    }
}
