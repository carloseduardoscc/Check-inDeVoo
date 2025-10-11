package br.com.ciaaerea.infra.cli.util.model;

import java.util.Objects;

public class MenuOption {
    private final String title;
    private final Runnable runnable;
    private final MenuOptionType type;

    public final static MenuOption EXIT = new MenuOption("Exit", MenuOptionType.NONE, () -> {});

    public MenuOption(String title, MenuOptionType type, Runnable runnable) {
        this.title = title;
        this.runnable = runnable;
        this.type = type;
    }

    public MenuOptionType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public void run() {
        runnable.run();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MenuOption that = (MenuOption) o;
        return Objects.equals(title, that.title) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type);
    }
}
