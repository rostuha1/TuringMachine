package view.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class AnimatedCircles {

    private static int spawnNodes = 1000;
    private static int circleSize = 3;

    static Color[] colors = {
            new Color(0.2,0.5,0.8, 1.0),
            new Color(0.3,0.2,0.7,1.0),
            new Color(0.8,0.3,0.9,1.0),
            new Color(0.4,0.3,0.9,1.0),
            new Color(0.2,0.5,0.7,1.0)
    };

    public static void createSpawnNodes(Pane container){
        Pane pane = new Pane();
        pane.setPrefSize(container.getPrefWidth(), container.getPrefHeight());
        container.getChildren().add(pane);

        for (int i = 0; i < spawnNodes; i++) {
            spawnNode(pane);
        }
    }

    private static void spawnNode(Pane container) {

        Circle node = new Circle(0);
        node.setManaged(false);

        node.setFill(colors[(int) (Math.random() * colors.length)]);
        node.setCenterX(Math.random() * container.getPrefWidth());
        node.setCenterY(Math.random() * container.getPrefHeight());
        container.getChildren().add(node);

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(node.radiusProperty(), 0),
                        new KeyValue(node.centerXProperty(), node.getCenterX()),
                        new KeyValue(node.centerYProperty(), node.getCenterY()),
                        new KeyValue(node.opacityProperty(), 0)),
                new KeyFrame(
                        Duration.seconds(5 + Math.random() * 5),
                        new KeyValue(node.opacityProperty(), Math.random()),
                        new KeyValue(node.radiusProperty(), Math.random() * circleSize)),
                new KeyFrame(
                        Duration.seconds(10 + Math.random() * 20),
                        new KeyValue(node.radiusProperty(), 0),
                        new KeyValue(node.centerXProperty(), Math.random() * container.getPrefWidth()),
                        new KeyValue(node.centerYProperty(), Math.random() * container.getPrefHeight()),
                        new KeyValue(node.opacityProperty(), 0))
        );

        timeline.setCycleCount(1);

        timeline.setOnFinished(event -> {
            container.getChildren().remove(node);
            spawnNode(container);
        });

        timeline.play();

    }


}
