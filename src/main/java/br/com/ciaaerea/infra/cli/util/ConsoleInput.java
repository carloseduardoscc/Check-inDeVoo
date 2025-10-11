package br.com.ciaaerea.infra.cli.util;



import br.com.ciaaerea.infra.cli.util.exceptions.ChoiceFromListCanceledException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class ConsoleInput {
    private static Scanner scan = new Scanner(System.in);

    public static String waitUserString() {
        return scan.nextLine().trim();
    }

    public static String waitUserString(boolean validateNotBlank) {
        while (true) {
            try {
                String input = waitUserString();
                if (validateNotBlank && (input.isBlank())) {
                    throw new IllegalArgumentException("Não esperado texto em branco");
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int waitUserInteger(int min, int max) {
        if (min > max) throw new IllegalArgumentException("Limite mínimo não deve ser maior que o máximo");

        while (true) {
            try {
                int input = waitUserInteger();
                if (input < min || input > max) {
                    throw new IllegalArgumentException();
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.printf("Somente permitido números entre %d e %d\n\nTente novamente: ", min, max);
            }
        }
    }

    public static String waitUserString(String regex) {
        while (true) {
            String input = scan.nextLine();
            if (!input.matches(regex)) {
                System.out.print("Opção inválida\n\nTente novamente: ");
            } else {
                return input;
            }
        }
    }

    public static int waitUserInteger() {
        while (true) {
            try {
                int num = scan.nextInt();
                return num;
            } catch (InputMismatchException e) {
                System.out.print("Somente números\n\nTente novamente: ");
            } finally {
                scan.nextLine();
            }
        }
    }

    public static void waitUserEnter() {
        System.out.print("\nDê enter para continuar...");
        scan.nextLine();
    }

    public static <T> T waitUserChoiceFromList(List<T> list) {
        List<String> toStrings = list.stream()
                .map(T::toString)
                .collect(Collectors.toCollection(ArrayList::new));

        toStrings.addLast("CANCELAR");

        ConsoleOutput.printList(toStrings);
        int inputNum = ConsoleInput.waitUserInteger(1, toStrings.size());
        boolean isCanceled = inputNum == toStrings.size();
        if (isCanceled){
            throw new ChoiceFromListCanceledException("Seleção cancelada");
        }

        return list.get(inputNum-1);
    }

    public static <T> T waitUserChoiceFromList(List<T> list, String title) {
        List<String> toStrings = list.stream()
                .map(T::toString)
                .collect(Collectors.toCollection(ArrayList::new));

        toStrings.addLast("CANCELAR");

        ConsoleOutput.printList(toStrings, title);
        System.out.print("\n-------> ");
        int inputNum = ConsoleInput.waitUserInteger(1, toStrings.size());
        boolean isCanceled = inputNum == toStrings.size();
        if (isCanceled){
            throw new ChoiceFromListCanceledException("Seleção cancelada");
        }

        return list.get(inputNum-1);
    }
}
