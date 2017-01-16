package controller;

import java.util.List;

public class Parser {

    public static void parseSymbols(List<Character> chars, String text) {
        chars.clear();
        text = text.replaceAll(" ", "");

        if (text.isEmpty()) return;
        String[] array = text.split(",");

        for (String letter : array) {
            chars.add(letter.charAt(0));
        }
    }

}
