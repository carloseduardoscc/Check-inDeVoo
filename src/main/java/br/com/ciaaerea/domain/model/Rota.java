package br.com.ciaaerea.domain.model;

public record Rota(String origem, String destino) {
    @Override
    public String toString() {
        return String.format("Rota [ De %s para %s ]",origem,destino);
    }
}
