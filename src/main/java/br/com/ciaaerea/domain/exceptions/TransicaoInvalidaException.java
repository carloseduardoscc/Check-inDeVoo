package br.com.ciaaerea.domain.exceptions;

public class TransicaoInvalidaException extends RuntimeException {
    public TransicaoInvalidaException(String message){
        super(message);
    }
}
