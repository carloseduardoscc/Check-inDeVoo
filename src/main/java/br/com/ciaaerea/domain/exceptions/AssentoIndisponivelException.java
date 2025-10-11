package br.com.ciaaerea.domain.exceptions;

public class AssentoIndisponivelException extends BusinessViolationException {
    public AssentoIndisponivelException(String s) {
        super(s);
    }
}
