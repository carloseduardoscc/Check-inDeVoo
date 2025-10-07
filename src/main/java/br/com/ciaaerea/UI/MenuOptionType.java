package br.com.ciaaerea.UI;

public enum MenuOptionType {
    VISUALIZAR("Visualizar"),
    CADASTRO("Cadastro"),
    RESERVAS("Reservas"),
    VISUALIZAR("Visualizar"),
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
