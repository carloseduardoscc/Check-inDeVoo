package br.com.ciaaerea.domain.exceptions;

public class BusinessViolationException extends RuntimeException{
    public BusinessViolationException(String message){
        super(message);
    }
}
