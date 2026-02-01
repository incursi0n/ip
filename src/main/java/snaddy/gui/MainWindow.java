package snaddy.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import snaddy.Snaddy;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Snaddy snaddy;

    /**
     * Initializes the UI and ensures the scroll pane stays at the bottom.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Snaddy instance.
     *
     * @param snaddy The chatbot instance.
     */
    public void setSnaddy(Snaddy snaddy) {
        this.snaddy = snaddy;
        dialogContainer.getChildren().add(DialogBox.getSnaddyDialog(snaddy.getWelcomeMessage()));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Snaddy's reply,
     * appends them to the dialog container, and clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = snaddy.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getSnaddyDialog(response)
        );

        userInput.clear();

        if (snaddy.isExit()) {
            Platform.exit();
        }
    }
}

