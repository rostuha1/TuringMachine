package controller;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Messenger {

    public static void messageInputError() {
        Notifications.create()
                .title("Алфавіт")
                .text("Невідповідність вхідних символів алфавіту")
                .hideAfter(Duration.seconds(0.5))
                .showError();
    }

    public static void messageOutputError() {
        Notifications.create()
                .title("Алфавіт")
                .text("Невідповідність вихідних символів алфавіту")
                .hideAfter(Duration.seconds(0.5))
                .showError();
    }

}
