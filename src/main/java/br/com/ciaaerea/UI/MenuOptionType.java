package br.com.ciaaerea.UI;

public enum MenuOptionType {
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
