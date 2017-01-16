package view.controls;

import controller.Algorithm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.WindowSettings;
import model.*;
import view.components.Component;
import view.components.ComponentBuilder;
import view.grid.Grid;

import java.util.Map;

public class SideMenu extends VBox {

    public static SideMenu instance = new SideMenu();
    private Insets insets = new Insets(10);
    private boolean isLambda = false;

    private static final double BUTTON_OPACITY = 0.5;

    private SideMenu() {
        setMaxHeight(WindowSettings.height - 100);

        TextField alphabetField = (TextField) ComponentBuilder.create(Component.FIELD, "Алфавіт");
        TextField symbolsField = (TextField) ComponentBuilder.create(Component.FIELD, "Список символів");
        Region addStateBtn = ComponentBuilder.getButton("Додати стан", 16, 300, 40, BUTTON_OPACITY);
        Region removeStateBtn = ComponentBuilder.getButton("Видалити стан", 16, 300, 40, BUTTON_OPACITY);
        Region startBtn = ComponentBuilder.getButton("Обчислити", 16, 300, 40, BUTTON_OPACITY);

        TextField input = (TextField) ComponentBuilder.create(Component.FIELD, "Вхідне слово");
        TextField output = (TextField) ComponentBuilder.create(Component.FIELD, "Вихідне слово");

        CheckBox isLambdaCheckBox = new CheckBox("Лямбда");
        isLambdaCheckBox.setFont(Settings.textFont);
        isLambdaCheckBox.setTextFill(Settings.textColor);

        VBox.setMargin(input, insets);
        VBox.setMargin(output, insets);
        VBox.setMargin(alphabetField, insets);
        VBox.setMargin(symbolsField, insets);
        VBox.setMargin(addStateBtn, insets);
        VBox.setMargin(removeStateBtn, insets);
        VBox.setMargin(startBtn, insets);
        VBox.setMargin(isLambdaCheckBox, insets);

        alphabetField.setOnKeyReleased(event -> Alphabet.instance.update(alphabetField.getText()));
        symbolsField.setOnKeyReleased(event -> AllSymbols.instance.update(symbolsField.getText(), isLambda));
        addStateBtn.setOnMouseClicked(event -> Grid.addRow());
        removeStateBtn.setOnMouseClicked(event -> Grid.removeRow());
        startBtn.setOnMouseClicked(event -> Algorithm.start(input, output));

        isLambdaCheckBox.setOnAction(event -> {
            isLambda = isLambdaCheckBox.isSelected();
            AllSymbols.instance.update(symbolsField.getText(), isLambda);
        });

        getChildren().add(alphabetField);
        getChildren().add(symbolsField);
        getChildren().add(isLambdaCheckBox);
        getChildren().add(input);
        getChildren().add(addStateBtn);
        getChildren().add(removeStateBtn);
        getChildren().add(startBtn);
        getChildren().add(output);
    }

}
