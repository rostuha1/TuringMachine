package model;

import controller.Parser;
import view.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class AllSymbols {
    private List<Character> symbols = new ArrayList<>();

    public static AllSymbols instance = new AllSymbols();
    private AllSymbols() {}

    public void update(String symbolsText, boolean isLambda) {
        Parser.parseSymbols(symbols, symbolsText);
        if (isLambda) symbols.add('λ');
        Grid.update();
    }

    public List<Character> getSymbols() {
        return symbols;
    }
}
