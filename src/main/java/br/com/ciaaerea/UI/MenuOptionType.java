package br.com.ciaaerea.UI;

public enum MenuOptionType {
    CADASTRAR("Cadastrar"),
    LISTAR("Listar"),
    NONE("");
    String name;

    MenuOptionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
