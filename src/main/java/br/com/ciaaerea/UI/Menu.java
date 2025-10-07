package br.com.ciaaerea.UI;

import br.com.ciaaerea.UI.exceptions.ChoiceFromListCanceledException;
import br.com.ciaaerea.domain.exceptions.BussinesViolationException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Menu {
    private String title;
    private List<MenuOption> options = new ArrayList<>();

    public Menu(String title, List<MenuOption> options) {
        this.title = title;
        this.options = new ArrayList<>(new HashSet<>(options));
        organizarOpcoes();
    }

    {
        options.add(MenuOption.EXIT);
    }

    public Menu(String title) {
        this.title = title;
    }

    public void open() {
        MenuOption lastOption = MenuOption.EXIT;
        do {
            ConsoleOptions.clearConsole();
            printMenuLogo();
            printMenuOptions();
            System.out.print("-----> ");
            MenuOption chosedOption = options.get(ConsoleInput.waitUserInteger(1, options.size())-1);
            ConsoleOptions.clearConsole();
            try {
                chosedOption.run();
            } catch (BussinesViolationException e) {
                System.err.printf("VIOLAÇÃO DE REGRA: %s\n",e.getMessage());
                ConsoleInput.waitUserEnter();
            } catch (ChoiceFromListCanceledException e){
            }
            lastOption = chosedOption;
        } while (!isExit(lastOption));
    }

    private boolean isExit(MenuOption option) {
        return option.equals(MenuOption.EXIT);
    }

    private void printMenuOptions() {
        MenuOption lastPrintedOption = null;
        MenuOptionType lastOptionType = null;
        for (int i = 0; i < options.size(); i++) {
            MenuOption option = options.get(i);
            if (lastPrintedOption == null || option.getType() != lastPrintedOption.getType()) {
                System.out.println(option.getType());
            }
            lastPrintedOption = option;
            System.out.printf("%3d - %s\n", i + 1, option.getTitle());
        }
    }

    private void printMenuLogo() {
        System.out.println(title);
    }

    public String getTitle() {
        return title;
    }

    public List<MenuOption> getOptions() {
        return new ArrayList<>(options);
    }

    public void addOption(MenuOption option) {
        if (!options.contains(option)) {
            this.options.add(option);
            organizarOpcoes();
        }
    }

    private void organizarOpcoes() {
        options.sort(Comparator.comparingInt(x -> x.getType().ordinal()));
    }

    public void removeOption(MenuOption option) {
        this.options.remove(option);
    }
}
