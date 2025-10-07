package br.com.ciaaerea.domain.exceptions;

public class PassageiroIsAlreadyInVooException extends BussinesViolationException {
    public PassageiroIsAlreadyInVooException(String s) {
        super(s);
    }
}
