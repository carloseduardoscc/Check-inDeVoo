package br.com.ciaaerea.UI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public final class ConsoleOutput {

    public static <T extends Object> void printList(List<T> list) {
        AtomicInteger idx = new AtomicInteger(1);

        int maxWidthCharString = list.stream().map(Objects::toString)
                .flatMap(s -> Arrays.stream(s.split("\\R")))
                .max(Comparator.comparingInt(String::length))
                .get().length();

        list.forEach(x -> System.out.printf("\n%3d %s\n%10s\n", idx.getAndIncrement(),"=".repeat(Math.max(0, maxWidthCharString-4)) , x.toString()));
    }

    public static <T extends Object> void printList(List<T> list, String title) {
        AtomicInteger idx = new AtomicInteger(1);

        int maxWidthCharString = list.stream().map(Objects::toString)
                .flatMap(s -> Arrays.stream(s.split("\\R")))
                .max(Comparator.comparingInt(String::length))
                .get().length();

        System.out.println("=".repeat(maxWidthCharString));
        String signCutoff = "=".repeat((maxWidthCharString - title.length()) / 2 - 1);
        System.out.printf("%s %s %s\n", signCutoff, title, signCutoff);
        System.out.println("=".repeat(maxWidthCharString));

        list.forEach(x -> System.out.printf("\n%3d %s\n%10s\n", idx.getAndIncrement(),"_".repeat(Math.max(0, maxWidthCharString-4)) , x.toString()));
    }
}
