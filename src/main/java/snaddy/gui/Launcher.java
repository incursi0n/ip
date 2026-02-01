package snaddy.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues when launching a JavaFX application.
 */
public class Launcher {
    /**
     * Entry point for launching the JavaFX application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

