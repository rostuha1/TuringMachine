package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class State {

    private Map<Character, Rule> rules = new LinkedHashMap<>();
    private int stateNumber;

    public State(int stateNumber) {
        this.stateNumber = stateNumber;
    }

    public Map<Character, Rule> getRules() {
        return rules;
    }

    public void setRules(Map<Character, Rule> rules) {
        this.rules = rules;
    }

    public Rule getRuleBySymbol(Character symbol) {
        return rules.get(symbol);
    }

    public void clear() {
        rules.clear();
    }

    public int getStateNumber() {
        return stateNumber;
    }

}
