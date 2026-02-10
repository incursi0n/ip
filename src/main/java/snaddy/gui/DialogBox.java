package snaddy.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of a label containing text from the speaker.
 * Supports asymmetric styling for user vs bot messages and error highlighting.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Label profilePicture;

    private DialogBox(String text, String styleClass, boolean showProfile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        this.getStyleClass().add(styleClass);

        if (showProfile) {
            profilePicture.setText("😢");
            profilePicture.setVisible(true);
        } else {
            profilePicture.setVisible(false);
            profilePicture.setManaged(false);
        }
    }

    /**
     * Checks if the text contains an error message.
     *
     * @param text The text to check.
     * @return true if the text indicates an error.
     */
    private static boolean isError(String text) {
        return text.contains("SAD!!!") || text.contains("Error") || text.contains("error");
    }

    /**
     * Returns a dialog box for the user's message.
     * User messages are styled with blue background and right alignment.
     *
     * @param text The user's message.
     * @return A dialog box aligned to the right with user styling.
     */
    public static DialogBox getUserDialog(String text) {
        DialogBox db = new DialogBox(text, "user-dialog", false);
        db.setAlignment(Pos.CENTER_RIGHT);
        return db;
    }

    /**
     * Returns a dialog box for Snaddy's message.
     * Bot messages are styled with light gray background and left alignment.
     * Error messages are highlighted with red styling.
     * Bot messages include a sad face profile picture.
     *
     * @param text Snaddy's response.
     * @return A dialog box aligned to the left with bot or error styling.
     */
    public static DialogBox getSnaddyDialog(String text) {
        String styleClass = isError(text) ? "error-dialog" : "bot-dialog";
        DialogBox db = new DialogBox(text, styleClass, true);
        db.setAlignment(Pos.CENTER_LEFT);
        return db;
    }
}
