package view.controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import jfxtras.labs.scene.control.window.Window;
import main.Main;
import model.Direction;
import model.StatesTable;
import view.components.ComponentBuilder;
import view.grid.Element;

public class StateManageWindow extends Window {

    private static Element currentElement;
    private static TextField field = new TextField();
    private static StateManageWindow instance = new StateManageWindow();

    private StateManageWindow() {
        setTitle("Зміна правила");

        setLayoutX(400);
        setLayoutY(30);

        field.setMinWidth(160);
        field.setAlignment(Pos.CENTER);

        field.setSkin(createDefaultSkin());

        field.setOnKeyReleased(event -> {
            String text = field.getText();
            text = text.replaceAll(" ", "");

            if (field.getText().isEmpty()) {
                currentElement.getRule().setSymbol(currentElement.getRule().getMainChar());
                currentElement.getRule().setDirection(Direction.N);
                currentElement.getRule().setNextState(null);
                currentElement.setText("");
                return;
            }

            String[] array = text.split(",");
            int i = 0;
            for (String symbol : array) {

                switch (i) {
                    case 0:
                        if (!symbol.isEmpty())
                            currentElement.getRule().setSymbol(symbol.charAt(0));
                        break;
                    case 1:
                        if (!symbol.isEmpty())
                            currentElement.getRule().setDirection(Direction.getDirection(symbol.charAt(0)));
                        break;
                    case 2: {
//                        try {
                        String number = symbol.replaceAll("q", "");
                        if (number.equals("!")) currentElement.getRule().setNextState(null);
                        else if (!number.isEmpty())
                            currentElement.getRule().setNextState(StatesTable.getStateByIndex(Integer.parseInt(number)));
//                        } catch (Exception ignored) {
//                        }
                    }
                    break;
                }
                i++;
            }

            currentElement.setText(field.getText());
        });

        Region addLambdaBtn = ComponentBuilder.getButton("λ", 30, 40, 40, 0.7, Color.LIGHTGREEN, Color.web("0x2A7A2A"));
        addLambdaBtn.setOnMouseClicked(event -> {
            field.appendText("λ");
            field.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, null, null, KeyCode.UNDEFINED, false, false, false, false));
        });

        HBox.setMargin(field, new Insets(10));
        HBox.setMargin(addLambdaBtn, new Insets(10));

        HBox hBox = new HBox(field, addLambdaBtn);
        getContentPane().getChildren().add(hBox);
    }

    public static void show(Element element) {
        currentElement = element;
        field.requestFocus();
        field.setText(element.getRule().toString());

        Main.getWrapper().getChildren().add(instance);
    }

    public static void hide() {
        Main.getWrapper().getChildren().remove(instance);
    }

}
