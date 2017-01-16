package model;

public enum Direction {
    L, R, N;

    public static Direction getDirection(char c) {
        switch (c) {
            case 'L': return L;
            case 'l': return L;
            case 'R': return R;
            case 'r': return R;
            case 'N': return N;
            case 'n': return N;
            default: return N;
        }
    }

}
