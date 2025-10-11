package br.com.ciaaerea.infra.cli.util;

public final class StringFormatter {
    public static String formatProp(String title, int maxTitleSpaceIdentation, String property){
        return String.format("%"+maxTitleSpaceIdentation+"s [ %s ]", title, property) + "\n";
    }
}
