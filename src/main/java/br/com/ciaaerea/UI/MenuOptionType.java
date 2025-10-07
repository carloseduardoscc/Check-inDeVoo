package br.com.ciaaerea.UI;

public enum MenuOptionType {
    VISUALIZAR("Visualizar"),
    CADASTRO("Cadastro"),
    RESERVAS("Reservas"),
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
