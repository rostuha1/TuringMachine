package view.grid;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import model.AllSymbols;
import model.Rule;
import model.State;
import model.StatesTable;

import java.util.Map;
import java.util.Set;

public class Grid extends GridPane {

    private static int rowsCount = 0;
    private static int columnCount = 0;
    private static Insets insets = new Insets(10);
    private static boolean isCanAddRow;

    public static Grid instance = new Grid();

    private Grid() {
        setMaxSize(0, 0);
    }

    public static void update() {

        instance.getChildren().clear();
        StatesTable.instance.clear();

        StatesTable.setSymbols(AllSymbols.instance.getSymbols());

        isCanAddRow = AllSymbols.instance.getSymbols().size() > 1;

        int i = 1;
        for (Character ch : AllSymbols.instance.getSymbols()) {
            Element e = new Element(String.valueOf(ch));
            Grid.setMargin(e, insets);
            instance.add(e, i++, 0);
        }

        columnCount = i;

        if (!isCanAddRow) return;

        for (i = 1; i < rowsCount + 1; i++) {
            Element q = new Element('q' + String.valueOf(i - 1));
            Grid.setMargin(q, insets);
            instance.add(q, 0, i);

            State state = new State(i - 1);

            int j = 1;
            for (Character ch : AllSymbols.instance.getSymbols()) {
                Element e = new Element(ch);
                state.getRules().put(ch, e.getRule());
                Grid.setMargin(e, insets);
                instance.add(e, j, i);
                j++;
            }
            StatesTable.instance.addState(state);
        }

    }

    public static void clear() {
        instance.getChildren().clear();
    }

    public static void addRow() {
        if (isCanAddRow) {
            rowsCount++;
            update();
        }
    }

    public static void removeRow() {
        if (rowsCount > 0) {
            rowsCount--;
            update();
        }
    }

}
