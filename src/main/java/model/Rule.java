package model;

public class Rule {

    private Character symbol = '\0';
    private Direction direction = Direction.N;
    private State nextState;

    private Character mainChar;

    public Rule(Character mainChar) {
        this.mainChar = mainChar;
    }

    public Rule(Character symbol, Direction direction, State nextState) {
        this.symbol = symbol;
        this.direction = direction;
        this.nextState = nextState;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public Character getMainChar() {
        return mainChar;
    }

    @Override
    public String toString() {
        if (symbol == '\0' && nextState == null) return "";
        return (symbol == '\0' ? "" : symbol) + ", " + direction + ", " + (nextState == null ? "!" : "q" + nextState.getStateNumber());
    }
}
