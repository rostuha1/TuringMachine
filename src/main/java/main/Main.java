package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Direction;
import view.animation.AnimatedCircles;
import view.controls.SideMenu;
import view.controls.StateManageWindow;
import view.grid.Grid;

public class Main extends Application {

    private static Pane wrapper = new Pane();
    private static BorderPane root = new BorderPane();
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        wrapper.setPrefSize(WindowSettings.width, WindowSettings.height);
        root.setPrefSize(WindowSettings.width, WindowSettings.height);
        wrapper.getChildren().add(root);
        scene = new Scene(wrapper);


        primaryStage.setFullScreen(WindowSettings.fullscreen);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setScene(scene);

        appInit();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void appInit() {

        root.setStyle("-fx-background-color: rgb(35, 40, 30)");
        AnimatedCircles.createSpawnNodes(root);

        BorderPane.setAlignment(SideMenu.instance, Pos.CENTER);
        BorderPane.setMargin(SideMenu.instance, new Insets(0, 0, 0, 50));
        BorderPane.setAlignment(Grid.instance, Pos.TOP_CENTER);

        root.setLeft(SideMenu.instance);
        root.setCenter(Grid.instance);
    }

    public static Scene getScene() {
        return scene;
    }

    public static BorderPane getRoot() {
        return root;
    }

    public static Pane getWrapper() {
        return wrapper;
    }
}
