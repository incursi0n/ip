package snaddy.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import snaddy.Snaddy;

/**
 * A GUI for Snaddy using FXML.
 */
public class Main extends Application {
    private final Snaddy snaddy = new Snaddy();

    /**
     * Starts the JavaFX application by loading the main window from FXML.
     *
     * @param stage Primary stage provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Snaddy");
            stage.setMinWidth(350);
            stage.setMinHeight(400);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setSnaddy(snaddy);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
