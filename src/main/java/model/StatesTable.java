package model;

import java.util.LinkedList;
import java.util.List;

public class StatesTable {

    private static List<Character> symbols;

    public static StatesTable instance = new StatesTable();
    private StatesTable() {}

    private LinkedList<State> states = new LinkedList<>();

    public State getState(int index) {
        return states.get(index);
    }

    public void addState(State state) {
        states.addLast(state);
    }

    public void removeState() {
        states.removeLast();
    }

    public void clear() {
        states.clear();
    }

    public static void setSymbols(List<Character> symbols) {
        StatesTable.symbols = symbols;
    }

    public static List<Character> getSymbols() {
        return symbols;
    }

    public LinkedList<State> getStates() {
        return states;
    }

    public static State getStateByIndex(int index) {
        for (State s: instance.states) {
            if (s.getStateNumber() == index) return s;
        }
        return null;
    }

    public static Character getSymbolByState(State state) {
        return null;
    }

}