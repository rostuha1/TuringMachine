package model;

import controller.Parser;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {

    private List<Character> alphabet = new ArrayList<>();

    public static Alphabet instance = new Alphabet();
    private Alphabet() {}

    public void update(String alphabetText) {
        Parser.parseSymbols(alphabet, alphabetText);
    }

    public List<Character> getSymbols() {
        return alphabet;
    }

    public boolean isInAlphabet(String word) {
        for (char c : word.toCharArray()) {
            if (!isInAlphabet(c)) return false;
        }
        return true;
    }

    public boolean isInAlphabet(Character symbol) {
        return alphabet.contains(symbol);
    }

}
