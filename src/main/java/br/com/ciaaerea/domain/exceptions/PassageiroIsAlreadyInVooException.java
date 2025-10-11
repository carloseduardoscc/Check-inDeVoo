package br.com.ciaaerea.domain.exceptions;

public class PassageiroIsAlreadyInVooException extends BusinessViolationException {
    public PassageiroIsAlreadyInVooException(String s) {
        super(s);
    }
}
