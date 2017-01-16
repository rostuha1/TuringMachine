package controller;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Mouse {

    private static void go(Node node, EventType<? extends MouseEvent> eventType) {
        Event.fireEvent(node, new MouseEvent(eventType, 0, 0, 0, 0, MouseButton.PRIMARY, 0, true, true, true, true, true, true, true, true, true, true, null));
    }

    public static void click(Node node) {
        go(node, MouseEvent.MOUSE_CLICKED);
    }

    public static void hover(Node node) {
        go(node, MouseEvent.MOUSE_ENTERED_TARGET);
    }

    public static void mouseExit(Node node) {
        go(node, MouseEvent.MOUSE_EXITED_TARGET);
    }

}

