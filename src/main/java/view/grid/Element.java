package view.grid;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Rule;
import view.controls.Settings;
import view.controls.StateManageWindow;

import java.util.ArrayList;

public class Element extends StackPane {

    public static Element checked = null;
    private static final Effect switchEffect = new DropShadow(10, Color.LIGHTGREEN);
    private Rule rule;
    private Text text = new Text();

    private static ArrayList<Element> elements = new ArrayList<>();

    {
        setMinSize(100, 40);
    }

    public Element(Character mainChar){
        setStyle("-fx-background-color: rgba(30, 255, 0, 0.6)");
        setOnMouseClicked(event -> click());

        rule = new Rule(mainChar);
        getChildren().add(text);
        elements.add(this);
    }

    public Element(String text) {
        setStyle("-fx-background-color: rgba(0, 100, 255, 0.6)");

        Text t = new Text(text);
        t.setFont(Settings.textFont);
        t.setFill(Settings.textColor);

        getChildren().add(t);
    }

    private void click() {
        if (this == checked) {
            switchOff(this);
            checked = null;
            StateManageWindow.hide();
        } else {
            if (checked != null) {
                switchOff(checked);
                StateManageWindow.hide();
            }
            checked = this;
            switchOn(checked);
            StateManageWindow.show(this);
        }
    }

    public static void switchOn(Element element) {
        element.setEffect(switchEffect);
    }
    public static void switchOff(Element element) {
        element.setEffect(null);
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public void setText(String text) {
        this.text.setText(text);
    }

}
