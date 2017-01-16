package view.controls;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Settings {

    public static final Font textFont = Font.font("Gill Sans", FontWeight.BOLD, 16);
    public static final Color textColor = Color.GREENYELLOW;

    public static Text getText(String text) {
        Text t = new Text(text);
        t.setFill(textColor);
        t.setFont(textFont);

        return t;
    }

    public static TextField getField() {
        TextField field = new TextField();
        field.setMaxWidth(30);
        field.setAlignment(Pos.CENTER);

        return field;
    }

}
