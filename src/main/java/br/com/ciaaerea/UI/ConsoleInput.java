package br.com.ciaaerea.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput {
    private static Scanner scan = new Scanner(System.in);

    public static String waitUserString() {
        return scan.nextLine().trim();
    }

    public static int waitUserInteger(int min, int max) {
        if (min >= max) throw new IllegalArgumentException("Limite mínimo não deve ser maior que máximo");

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

    public static int waitUserInteger() {
        while (true) {
            try {
                int num = scan.nextInt();
                scan.nextLine();
                return num;
            } catch (InputMismatchException e) {
                System.out.print("Somente números\n\nTente novamente: ");
            }
        }
    }

    public static void waitUserEnter(){
        System.out.print("\nDê enter para continuar...");
        scan.nextLine();
    }
}
