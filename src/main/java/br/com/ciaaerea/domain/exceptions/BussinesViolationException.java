package br.com.ciaaerea.domain.exceptions;

public class BussinesViolationException extends RuntimeException{
    public BussinesViolationException(String message){
        super(message);
    }
}
