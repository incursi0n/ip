package snaddy;

import java.io.File;

import snaddy.command.Command;
import snaddy.exception.SnaddyException;
import snaddy.util.Parser;
import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents the main application class for Snaddy, a task management application.
 * Handles initialization, command processing, and the main execution loop.
 */
public class Snaddy {
    private static final String FILE_PATH = "." + File.separator + "data" + File.separator + "snaddy.txt";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final String startupMessage;
    private boolean isExit;

    /**
     * Constructs a Snaddy instance with the specified file path for data storage.
     * Initializes the UI, storage, and task list. If loading fails, starts with an empty task list.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Snaddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        String message = "";
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (SnaddyException e) {
            loadedTasks = new TaskList();
            message = ui.showLine()
                    + ui.showLoadingError()
                    + ui.showLine();
        }
        tasks = loadedTasks;
        startupMessage = message;
    }

    /**
     * Constructs a Snaddy instance using the default file path for data storage.
     */
    public Snaddy() {
        this(FILE_PATH);
    }

    /**
     * Returns the welcome message to be shown to the user.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome() + startupMessage;
    }

    /**
     * Returns whether the application should exit.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Generates a response for the user's input, updating application state as needed.
     *
     * @param input The full command string entered by the user.
     * @return The response message to be shown to the user.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
            isExit = command.isExit();
            return ui.showLine() + response + ui.showLine();
        } catch (SnaddyException e) {
            return ui.showLine() + ui.showError(e.getMessage()) + ui.showLine();
        } catch (Exception e) {
            return ui.showLine()
                    + ui.showError("SAD!!! An error occurred: " + e.getMessage())
                    + ui.showLine();
        }
    }

    /**
     * Runs the application using the command-line interface.
     * Displays welcome message, reads and processes user commands until exit command is received.
     */
    public void run() {
        System.out.print(getWelcomeMessage());
        while (!isExit) {
            String fullCommand = ui.readCommand();
            System.out.print(getResponse(fullCommand));
        }
        ui.close();
    }

    /**
     * Entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Snaddy(FILE_PATH).run();
    }
}
