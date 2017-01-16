package controller;

import javafx.scene.control.TextField;
import model.Alphabet;
import model.Rule;
import model.State;
import model.StatesTable;

import java.util.HashMap;

public class Algorithm {

    public static Algorithm instance = new Algorithm();

    private static int position;
    private static State nextState;

    private Algorithm() {
    }

    public static void start(TextField input, TextField output) {
        position = 1;
        if (input.getText().isEmpty()) return;

        if (!Alphabet.instance.getSymbols().isEmpty() && !isBelongToAlphabet(input.getText().replaceAll(" ", "").split(","))) {
            Messenger.messageInputError();
        }

        State firstState = StatesTable.getStateByIndex(0);
        String word = 'λ' + input.getText() + 'λ';

        StringBuilder result = run(firstState, new StringBuilder(word));
        System.out.println(result);
        System.out.println(position);

        while (nextState != null) {
            result = run(nextState, new StringBuilder(result));
        }

        output.setText(result.toString().replaceAll("λ", ""));

        if (!Alphabet.instance.getSymbols().isEmpty() && !isBelongToAlphabet(output.getText().toCharArray())) {
            Messenger.messageInputError();
        }
    }

    private static StringBuilder run(State state, StringBuilder word) {
        if (state == null) return word;

        Rule rule;

        if (position == 0 || position == word.length() - 1) {
            rule = getRuleByCurrentChar(state, 'λ');
        } else rule = getRuleByCurrentChar(state, word.charAt(position));

        if (rule == null) {
            return new StringBuilder("");
        }


        return applyRule(rule, word);
    }

    private static Rule getRuleByCurrentChar(State state, Character charByPos) {
        for (HashMap.Entry<Character, Rule> rule : state.getRules().entrySet()) {
            if (rule.getValue().getMainChar().equals(charByPos)) {
                return rule.getValue();
            }
        }
        return null;
    }

    private static StringBuilder applyRule(Rule rule, StringBuilder word) {

        if (rule.getSymbol() == '\0') {
            nextState = null;
            return word;
        }

        if (rule.getMainChar().equals('λ')) {
            if (position == word.length() - 1 && !rule.getSymbol().equals('λ')) {
                word.setCharAt(position, rule.getSymbol());
                if (!rule.getSymbol().equals('λ')) word.append('λ');
            } else if (position == 0) {
                word.setCharAt(position, rule.getSymbol());
                if (!rule.getSymbol().equals('λ')) {
                    word.insert(0, 'λ');
                    position = 1;
                }
            }
        } else word.setCharAt(position, rule.getSymbol());

        changePosition(rule);
        nextState = rule.getNextState();

        return word;
    }

    private static void changePosition(Rule rule) {
        switch (rule.getDirection()) {
            case L:
                position--;
                break;
            case R:
                position++;
                break;
        }
    }

    private static void deleteLambdas(StringBuilder sb) {
        int index;
        while ((index = sb.lastIndexOf("λ")) != -1) {
            sb.replace(index, index + 1, "");
        }
    }

    private static StringBuilder addLambdas(StringBuilder sb) {
        return new StringBuilder('λ' + sb.toString().replaceAll("λ", "") + 'λ');
    }

    private static boolean isBelongToAlphabet(String[] split) {
        for (String ch : split) {
            if (!Alphabet.instance.getSymbols().contains(ch.charAt(0))) {
                return false;
            }
        }

        return true;
    }

    private static boolean isBelongToAlphabet(char[] chars) {
        for (char ch : chars) {
            if (!Alphabet.instance.getSymbols().contains(ch)) {
                return false;
            }
        }

        return true;
    }

}
