package br.com.ciaaerea.UI;

public final class StringFormatter {
    public static String formatProp(String title, int maxTitleSpaceIdentation, String property){
        return String.format("%"+maxTitleSpaceIdentation+"s [ %s ]", title, property) + "\n";
    }
}
